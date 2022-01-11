package com.nicomahnic.enerfiv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long = 2000 // 2 sec


    companion object {
        fun after(delay: Long, process: () -> Unit) {
            Handler(Looper.getMainLooper()).postDelayed({
                process()
            }, delay)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        after(SPLASH_TIME_OUT){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}