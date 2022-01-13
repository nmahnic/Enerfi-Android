package com.nicomahnic.enerfiv2.ui.splashFragments.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.findNavController
import androidx.preference.PreferenceManager
import com.nicomahnic.enerfiv2.R
import com.nicomahnic.enerfiv2.ui.activities.MainActivity

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val SPLASH_TIME_OUT:Long = 500 // 2 sec

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val userMail = prefs.getString("userMail","")
        val password = prefs.getString("password","")

        after(SPLASH_TIME_OUT){
            if(userMail!!.isBlank() && password!!.isBlank()) {
                val action = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
                view.findNavController().navigate(action)
            } else {
                after(SPLASH_TIME_OUT) {
                    startActivity(Intent(context, MainActivity::class.java))
                    activity?.finish()
                }
            }
        }
    }

    companion object {
        fun after(delay: Long, process: () -> Unit) {
            Handler(Looper.getMainLooper()).postDelayed({
                process()
            }, delay)
        }
    }
}