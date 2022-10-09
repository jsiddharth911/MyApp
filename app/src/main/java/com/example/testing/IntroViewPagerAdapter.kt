package com.example.testing

import android.content.Context
import com.example.testing.Intro
import androidx.viewpager.widget.PagerAdapter
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.testing.R

class IntroViewPagerAdapter(var context: Context, var introList: List<Intro>) : PagerAdapter() {
    override fun getCount(): Int {
        return introList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.image, null)
        val image = view.findViewById<ImageView>(R.id.Image)
        val tittle = view.findViewById<TextView>(R.id.title)
        val desc= view.findViewById<TextView>(R.id.desc)
        //TextView title = view.findViewById(R.id.title);
        // TextView desc = view.findViewById(R.id.desc);
        image.setImageResource(introList[position].imageUrl)
        tittle.text=introList[position].tittle
        desc.text=introList[position].desc
        container.addView(view)
        return view
    }
}