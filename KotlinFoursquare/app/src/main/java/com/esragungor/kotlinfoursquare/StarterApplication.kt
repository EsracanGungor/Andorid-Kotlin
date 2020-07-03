package com.esragungor.kotlinfoursquare

import android.app.Application
import com.parse.Parse

class StarterApplication :Application() {

    override fun onCreate() {
        super.onCreate()

        Parse.initialize(Parse.Configuration.Builder(this)
            .applicationId("cBIdgzc7b4ZyGvPoMfSEEViuyvxIRV9KwXdG82Ql")
            .clientKey("jrUOOaRHtkMWZIORJqMLPTZtw08DQMVB3APh4Bck")
            .server("https://parseapi.back4app.com/")
            .build())
    }
}