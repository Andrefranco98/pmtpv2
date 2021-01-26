package com.androiddevs.firebasenotifications

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
    }

    fun notifscreenbtn(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }


}