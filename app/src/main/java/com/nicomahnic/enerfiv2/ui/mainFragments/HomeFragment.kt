package com.nicomahnic.enerfiv2.ui.mainFragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.nicomahnic.enerfiv2.R
import com.nicomahnic.enerfiv2.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

//        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
//        blankPasswd(prefs)

        binding.txtTest.text = "HOLA"
    }

    private fun blankPasswd(prefs: SharedPreferences){
        with (prefs.edit()) {
            putString("username", "")
            putString("password", "")
            apply()
        }
    }

}