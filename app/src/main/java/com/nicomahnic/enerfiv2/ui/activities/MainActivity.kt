package com.nicomahnic.enerfiv2.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }
}