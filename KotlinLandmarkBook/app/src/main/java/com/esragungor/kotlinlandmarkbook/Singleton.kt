package com.esragungor.kotlinlandmarkbook

import android.graphics.Bitmap

class Singleton {
    companion object Selected {
        var selectedImage: Bitmap? = null
    }
}