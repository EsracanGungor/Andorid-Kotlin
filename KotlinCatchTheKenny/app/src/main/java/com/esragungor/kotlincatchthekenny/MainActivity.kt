package com.esragungor.kotlincatchthekenny

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var score = 0;
    var imageArray = ArrayList<ImageView>()
    var handler = Handler()
    var runnable = Runnable { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageArray.add(iv_kenny)
        imageArray.add(iv_kenny1)
        imageArray.add(iv_kenny2)
        imageArray.add(iv_kenny3)
        imageArray.add(iv_kenny4)
        imageArray.add(iv_kenny5)
        imageArray.add(iv_kenny6)
        imageArray.add(iv_kenny7)
        imageArray.add(iv_kenny8)

        hideImages()

        object : CountDownTimer(15500, 1000) {
            override fun onFinish() {
                tv_time.text = "Time: 0"

                handler.removeCallbacks(runnable)

                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }

                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Restart The Game?")
                alert.setPositiveButton("Yes") { dialog, which ->
                    val intent = intent
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("No") { dialog, which ->
                    Toast.makeText(this@MainActivity, "Game Over", Toast.LENGTH_LONG).show()
                }

                alert.show()
            }

            override fun onTick(millisUntilFinished: Long) {
                tv_time.text = "Time:" + millisUntilFinished / 1000
            }

        }.start()

    }


    fun hideImages() {

        runnable = object : Runnable {
            override fun run() {

                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }

                val random = Random()
                val randomIndex = random.nextInt(8-0)
                imageArray[randomIndex].visibility = View.VISIBLE

                handler.postDelayed(runnable, 500)

            }

        }
        handler.post(runnable)

    }

    fun increaseScore(view: View) {
        score = score + 1
        tv_score.text = "Score: $score"
    }
}