package com.androiddevs.firebasenotifications

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


const val TOPIC = "/topics/myTopic2"

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth


    val TAG = "MainActivity"





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()

        FirebaseService.sharedPref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            FirebaseService.token = it.token
            etToken.setText(it.token)

            val sdf = SimpleDateFormat("yyyy:MM:dd-HH:mm:ss")
            val currentDateandTime = sdf.format(Date())
            etMessage.setText(currentDateandTime)

        }

        fun saveHero(){
            val sdf = SimpleDateFormat("yyyy:MM:dd-HH:mm:ss")
            val currentDateandTime = sdf.format(Date())
            val ref = FirebaseDatabase.getInstance().getReference(("Sleeps"))
            val heroId = ref.push().key

            val hero = Hero(heroId, currentDateandTime)
            if (heroId != null) {
                ref.child(heroId).setValue(hero)
            }
        }


        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)

        btnSend.setOnClickListener {
            val title = etTitle.text.toString()
            val message = etMessage.text.toString()
            val recipientToken = etToken.text.toString()
            saveHero()
            if(title.isNotEmpty() && message.isNotEmpty() && recipientToken.isNotEmpty()) {
                PushNotification(
                    NotificationData(title, message),
                    recipientToken
                ).also {
                    sendNotification(it)
                }
            }
            ringtone()
        }

            btnLogout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()

        }

    }



    private fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = RetrofitInstance.api.postNotification(notification)
            if(response.isSuccessful) {
                Log.d(TAG, "Response: ${Gson().toJson(response)}")
            } else {
                Log.e(TAG, response.errorBody().toString())
            }
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }

     private fun ringtone(){
        try{
            val track: MediaPlayer? = MediaPlayer.create(applicationContext, R.raw.baby)
            track?.start()
        } catch (e: Exception){
            Toast.makeText(applicationContext, "Failed to play the ringtone!", Toast.LENGTH_SHORT).show()
        }
    }

    fun homepagescreen(view: View) {
        startActivity(Intent(this, HomePageActivity::class.java))
        finish()
    }

}
