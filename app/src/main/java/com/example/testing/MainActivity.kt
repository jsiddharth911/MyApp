package com.example.testing

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.MediaController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mediaControls: MediaController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            val intent = Intent(this, OnBoarding::class.java)
            startActivity(intent)
            finish()
        }, 2000)

        if (mediaControls == null) {
            mediaControls = MediaController(this)
            mediaControls!!.setAnchorView(this.splashVideo)
        }
        splashVideo!!.setMediaController(mediaControls)

        splashVideo!!.setVideoURI(
            Uri.parse("android.resource://"
                    + packageName + "/" + R.raw.siro_splash_icon))

        splashVideo!!.requestFocus()
        splashVideo!!.start()
    }
}