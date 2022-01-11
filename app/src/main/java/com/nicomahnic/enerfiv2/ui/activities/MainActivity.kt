package com.nicomahnic.enerfiv2.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.nicomahnic.enerfiv2.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        with (prefs.edit()) {
            putString("username", "")
            putString("password", "")
            apply()
        }
    }
}