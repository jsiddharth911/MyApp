package com.example.testing

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import java.util.ArrayList


class OnBoarding : AppCompatActivity() {
   private var introViewPagerAdapter: IntroViewPagerAdapter? = null
   private var tabLayout: TabLayout? = null
    var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (restorePref()) {
            val i = Intent(this@OnBoarding, SignIn::class.java)
            startActivity(i)
            finish()
        }
        setContentView(R.layout.activity_on_boarding)

        val introList: MutableList<Intro> = ArrayList()


        introList.add(Intro("Ready To Travel","hey", R.drawable.travel))
        introList.add(Intro("Select The Date","day",R.drawable.date))
        introList.add(Intro("Choose Your Hotels","say", R.drawable.hotel))
        introList.add(Intro("Enjoy Hotel Facilities","say", R.drawable.facilities))
            // introList.add(Intro(R.drawable.image4))


        val viewPager =findViewById<ViewPager>(R.id.screenPager)
        val next = findViewById<TextView>(R.id.button)
        tabLayout = findViewById(R.id.tabIndicator)
        introViewPagerAdapter = IntroViewPagerAdapter(this, introList)
        viewPager.adapter = introViewPagerAdapter
        tabLayout!!.setupWithViewPager(viewPager)
        position = viewPager.currentItem
        next.setOnClickListener {
            if (position < introList.size) {
                position++
                viewPager.currentItem = position
            }
            if (position == introList.size) {
                savePrefData()
                val i = Intent(this@OnBoarding, SignIn::class.java)
                startActivity(i)
                finish()
            }
        }
        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                position = tab.position
                if (tab.position == introList.size - 1) {
                    next.text = "Get Started"
                } else {
                    next.text = "Next"
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun savePrefData() {
        val sharedPreferences = applicationContext.getSharedPreferences("mypref", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isFristTimeLaunch", true)
        editor.apply()
    }

    private fun restorePref(): Boolean {
        val sharedPreferences = applicationContext.getSharedPreferences("mypref", MODE_PRIVATE)
        return sharedPreferences.getBoolean("isFristTimeLaunch", false)
    }
}