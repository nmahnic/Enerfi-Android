package com.nicomahnic.enerfiv2.ui.mainFragments.logout

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.nicomahnic.enerfiv2.R
import com.nicomahnic.enerfiv2.databinding.FragmentLogoutBinding

class LogoutFragment : Fragment(R.layout.fragment_logout) {

    private lateinit var binding : FragmentLogoutBinding
    private lateinit var v : View

    override fun onViewCreated (view: View, savedInstanceState: Bundle?) {
        v = view

        binding = FragmentLogoutBinding.bind(view)

        binding.btnLogout.setOnClickListener(clickListenerLogout)

    }

    private val clickListenerLogout = View.OnClickListener {
        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        with(prefs.edit()) {
            putString("userMail", "")
            putString("password", "")
            apply()
        }
        activity?.finish()
    }

}