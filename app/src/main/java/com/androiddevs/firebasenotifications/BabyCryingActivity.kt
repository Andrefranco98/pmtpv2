package com.androiddevs.firebasenotifications

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class BabyCryingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baby_crying)
    }


    fun homescreen(view: View) {
        try{
            val track: MediaPlayer? = MediaPlayer.create(applicationContext, R.raw.baby)
            track?.stop()
        } catch(e: Exception){
            Toast.makeText(applicationContext,"Failed to play the ringtone!", Toast.LENGTH_SHORT).show()
        }

        startActivity(Intent(this, HomePageActivity::class.java))
        finish()
    }
}