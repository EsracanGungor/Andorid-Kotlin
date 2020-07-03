package com.esragungor.kotlinfoursquare

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import kotlinx.android.synthetic.main.activity_place_name.*
import java.util.jar.Manifest

var globalName = ""
var globalType = ""
var globalAtmosphere = ""
var globalImage: Bitmap? = null

class PlaceNameActivity : AppCompatActivity() {
    var chosenImage: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_name)
    }

    fun selectImage(view: View) {

        if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 2)
        } else {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 1)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 2) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, 1)
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            val selected = data.data

            try {

                chosenImage = MediaStore.Images.Media.getBitmap(
                    this
                        .contentResolver, selected
                )
                imageView.setImageBitmap(chosenImage)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


        super.onActivityResult(requestCode, resultCode, data)
    }

    fun next(view: View) {

        globalImage = chosenImage
        globalName = et_place_name.text.toString()
        globalType = et_place_type.text.toString()
        globalAtmosphere = et_place_atmosphere.text.toString()

        val intent = Intent(applicationContext,MapsActivity::class.java)
        startActivity(intent)


    }
}