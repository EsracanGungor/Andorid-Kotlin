package com.esragungor.kotlincurrencyconverter

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getRates(view: View) {

        val download = Download()
        try {
            val url =
                "http://data.fixer.io/api/latest?access_key=e02d45855d5d8dee6278f727b095eb8c&format=1"
            download.execute(url)
        } catch (e: Exception) {

        }

    }

    inner class Download : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String?): String {

            var result = ""

            var url: URL
            var httpUrlConnectException: HttpURLConnection
            try {

                url = URL(params[0])
                httpUrlConnectException = url.openConnection() as HttpURLConnection
                val inputStream = httpUrlConnectException.inputStream
                val inputStreamReader = InputStreamReader(inputStream)

                var data = inputStreamReader.read()

                while (data > 0) {

                    val character = data.toChar()
                    result += character
                    data = inputStreamReader.read()
                }

                return result
            } catch (e: Exception) {

                return result
            }


        }

        override fun onPostExecute(result: String?) {

            try {
                val jsonObject = JSONObject(result)
                val base = jsonObject.getString("base")
                val rates = jsonObject.getString("rates")

                val jsonObject1 = JSONObject(rates)
                val turkishLira = jsonObject1.getString("TRY")
                val cad = jsonObject1.getString("TRY")
                val gbp = jsonObject1.getString("TRY")
                val usd = jsonObject1.getString("TRY")
                val chf = jsonObject1.getString("TRY")

                tv_try.text = "TRY = " + turkishLira
                tv_cad.text = "CAD = " + cad
                tv_chf.text = "CHF = " + chf
                tv_gbp.text = "GBP = " + gbp
                tv_usd.text = "USD = " + usd
            } catch (e: Exception) {

            }
            super.onPostExecute(result)
        }
    }
}