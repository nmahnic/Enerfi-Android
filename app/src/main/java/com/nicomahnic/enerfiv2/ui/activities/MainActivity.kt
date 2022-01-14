package com.nicomahnic.enerfiv2.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.nicomahnic.enerfiv2.R
import com.nicomahnic.enerfiv2.databinding.ActivityMainBinding
import com.nicomahnic.enerfiv2.ui.mainFragments.deviceregister.DeviceRegisterFragment
import com.nicomahnic.enerfiv2.ui.mainFragments.home.HomeFragment
import com.nicomahnic.enerfiv2.ui.mainFragments.logout.LogoutFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomMenu.setOnItemSelectedListener { id ->
            when (id) {
                R.id.home -> {
                    val fragment = HomeFragment.newInstance()
                    openFragment(fragment)
                    Log.d("NM", "MENU -> first: HOME")
                }
                R.id.favorites -> {
                    val fragment = LogoutFragment.newInstance()
                    openFragment(fragment)
                    Log.d("NM", "MENU -> first:LOGOUT")
                }
                R.id.settings -> {
                    val fragment = DeviceRegisterFragment.newInstance()
                    openFragment(fragment)
                    Log.d("NM", "MENU -> first:SETTINGS")
                }
                else -> {
                    Log.d("NM", "MENU -> first: UNKNOWN ${id}")
                    false
                }
            }
            binding.bottomMenu.setItemSelected(R.id.homeFragment)
        }
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}