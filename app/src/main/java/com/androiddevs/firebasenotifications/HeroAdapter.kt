package com.androiddevs.firebasenotifications

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class HeroAdapter(val mCtx: Context, val layoutResId: Int, val heroList: List<Hero>) :
    ArrayAdapter<Hero>(mCtx,layoutResId,heroList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId, null)


        val textViewTime = view.findViewById<TextView>(R.id.textViewTime)


        val hero = heroList[position]

        textViewTime.text = hero.timestamp

        return view;
    }
}


