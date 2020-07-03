package com.esragungor.kotlinfoursquare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.parse.*
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        ParseAnalytics.trackAppOpenedInBackground(intent)

    }

    fun signIn(view:View){
        ParseUser.logInInBackground(et_userName.text.toString(),et_password.text.toString(), LogInCallback { user, e ->

            if (e!= null) {
                Toast.makeText(applicationContext,e.localizedMessage,Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext,"Welcome " + user.username.toString(),Toast.LENGTH_LONG).show()
                val intent = Intent(applicationContext,LocationsActivity::class.java)
                startActivity(intent)
            }


        })
    }

    fun signUp(view:View){

        val user=ParseUser()
        user.username=et_userName.text.toString()
        user.setPassword(et_password.text.toString())

        user.signUpInBackground {e:ParseException?->
            if (e!=null){
                Toast.makeText(applicationContext,e.localizedMessage,Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(applicationContext,"User Created",Toast.LENGTH_LONG).show()
                val intent=Intent(applicationContext,LocationsActivity::class.java)
                startActivity(intent)
            }
        }
    }
}