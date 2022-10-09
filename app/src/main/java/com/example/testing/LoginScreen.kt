package com.example.testing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_login_screen.*
import kotlin.math.abs

class LoginScreen : AppCompatActivity() {

    private lateinit var  viewPager2: ViewPager2
    private lateinit var handler : Handler
    private lateinit var imageList:ArrayList<Int>
    private lateinit var adapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        textView.setOnClickListener(){
            val i = Intent(this, Home::class.java)
            startActivity(i)
            finish()
        }
        createAccount.setOnClickListener(){
            val i = Intent(this, CreateAccount::class.java)
            startActivity(i)
            finish()
        }
        login.setOnClickListener(){
            val i = Intent(this, SignIn::class.java)
            startActivity(i)
            finish()
        }

        init()
        setUpTransformer()
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable , 2000)
            }
        })
    }
    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }
    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable , 2000)
    }
    private val runnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }
    private fun setUpTransformer(){
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }

        viewPager2.setPageTransformer(transformer)
    }

    private fun init(){
        viewPager2 = findViewById(R.id.idViewPager)
        handler = Handler(Looper.myLooper()!!)
        imageList = ArrayList()


        imageList.add(R.drawable.image1)
        imageList.add(R.drawable.image2)
        imageList.add(R.drawable.image3)
        imageList.add(R.drawable.image4)



        adapter = ViewPagerAdapter(imageList, viewPager2)
        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }
}