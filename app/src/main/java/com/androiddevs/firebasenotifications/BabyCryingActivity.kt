package com.androiddevs.firebasenotifications

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class BabyCryingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baby_crying)
    }

    fun homescreen(view: View) {
        startActivity(Intent(this, HomePageActivity::class.java))
        finish()
    }
}