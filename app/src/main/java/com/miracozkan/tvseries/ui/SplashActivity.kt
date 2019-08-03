package com.miracozkan.tvseries.ui

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.miracozkan.tvseries.R
import com.miracozkan.tvseries.reciever.InternetConnectionReciever

class SplashActivity : AppCompatActivity() {

    private val internetConnectionReceiver by lazy { InternetConnectionReciever() }

    private val SPLASH_TIME_OUT: Long = 3000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(internetConnectionReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(internetConnectionReceiver)
    }
}