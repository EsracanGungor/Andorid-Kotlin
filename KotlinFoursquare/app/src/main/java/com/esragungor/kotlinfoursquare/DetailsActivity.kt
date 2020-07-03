package com.esragungor.kotlinfoursquare

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity(), OnMapReadyCallback {
    var chosenPlace=""
    private lateinit var mMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val intent=intent
        chosenPlace=intent.getStringExtra("name")


    }

    override fun onMapReady(p0: GoogleMap) {
        mMap=p0

        var query=ParseQuery<ParseObject>("Locations")
        query.whereEqualTo("name",chosenPlace)
        query.findInBackground { objects, e ->
            if (e!=null){
                Toast.makeText(applicationContext,e.localizedMessage,Toast.LENGTH_LONG).show()
            }else{
                if(objects.size>0){
                    for (parseObject in objects){
                        val image=parseObject.get("image") as ParseFile
                        image.getDataInBackground { data,e->
                            if (e!=null){
                                Toast.makeText(applicationContext,e.localizedMessage,Toast.LENGTH_LONG).show()
                            }else{

                                val bitmap=BitmapFactory.decodeByteArray(data,0,data.size)
                                iv_details.setImageBitmap(bitmap)
                                val name=parseObject.get("name") as String
                                val latitude=parseObject.get("latitude") as String
                                val longitude=parseObject.get("longitude") as String
                                val type=parseObject.get("type") as String
                                val atmosphere=parseObject.get("atmoshpere") as String

                                tv_details_name.text=name
                                tv_details_type.text=type
                                tv_details_atmosphere.text=atmosphere

                                val latitudeDouble=latitude.toDouble()
                                val longitudeDouble=longitude.toDouble()

                                val chosenLocation=LatLng(latitudeDouble,longitudeDouble)
                                mMap.addMarker(MarkerOptions().position(chosenLocation).title(name))
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(chosenLocation,17f))
                            }
                        }
                    }
                }
            }
        }
    }
}