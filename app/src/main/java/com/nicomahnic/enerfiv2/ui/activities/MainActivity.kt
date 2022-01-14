package com.nicomahnic.enerfiv2.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.nicomahnic.enerfiv2.R
import com.nicomahnic.enerfiv2.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomMenu.setOnItemSelectedListener { id ->
            val option = when (id) {
                R.id.home -> "Home"
                R.id.favorites -> "Favorites"
                R.id.settings -> "Settings"
                else -> "Known"
            }

            Log.d("NM", "MENU -> first:${option}")

        }
    }
}