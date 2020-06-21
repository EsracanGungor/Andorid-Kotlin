package com.esragungor.kotlinlandmarkbook

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var landmarkNames = ArrayList<String>()
        landmarkNames.add("Pisa")
        landmarkNames.add("Colosseum")
        landmarkNames.add("Eiffel")
        landmarkNames.add("London Bridge")

        val pisa = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.pisa)
        val colosseum =
            BitmapFactory.decodeResource(applicationContext.resources, R.drawable.colosseum)
        val eiffel = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.eiffel)
        val londonBridge =
            BitmapFactory.decodeResource(applicationContext.resources, R.drawable.london)

        var landMarkImages = ArrayList<Bitmap>()
        landMarkImages.add(pisa)
        landMarkImages.add(colosseum)
        landMarkImages.add(eiffel)
        landMarkImages.add(londonBridge)

        //  val adapter = ArrayAdapter(this, android.R.layout.list_row,R.id.tv_landmark_name, landmarkNames)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, landmarkNames)
        lv_landmark.adapter = adapter

        lv_landmark.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val intent = Intent(applicationContext, DetailsActivity::class.java)

                intent.putExtra("name", landmarkNames[position])

                val singleton = Singleton.Selected
                singleton.selectedImage = landMarkImages[position]

                startActivity(intent)
            }
    }


}