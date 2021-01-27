package com.androiddevs.firebasenotifications

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.TextView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_register.*

class HomePageActivity : AppCompatActivity() {

    lateinit var ref: DatabaseReference
    lateinit var heroList: MutableList<Hero>
    lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)


        heroList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference(("Sleeps"))
        listView = findViewById(R.id.listView)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
            if(p0!!.exists()){
                for(h in p0.children){
                    val hero = h.getValue(Hero::class.java)
                    heroList.add(hero!!)
                }

                val adapter = HeroAdapter(applicationContext, R.layout.sleeps, heroList)
                listView.adapter = adapter

            }

            }


            override fun onCancelled(error: DatabaseError) {
            }
        })
    }


    fun notifscreenbtn(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }


}