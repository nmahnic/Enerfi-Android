package com.nicomahnic.enerfiv2.ui.mainFragments.tempmenu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.preference.PreferenceManager
import com.nicomahnic.enerfiv2.R
import com.nicomahnic.enerfiv2.databinding.FragmentTempMenuBinding

class TempMenuFragment : Fragment(R.layout.fragment_temp_menu) {

    private lateinit var binding : FragmentTempMenuBinding
    private lateinit var v : View

    override fun onViewCreated (view: View, savedInstanceState: Bundle?) {
        v = view

        binding = FragmentTempMenuBinding.bind(view)

        binding.btnLogout.setOnClickListener(clickListenerLogout)
        binding.btnAddDevice.setOnClickListener(clickListenerConnect)
        binding.btnDevices.setOnClickListener(clickListenerDevices)

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

    private val clickListenerConnect = View.OnClickListener {
        val action = TempMenuFragmentDirections.actionTempMenuFragmentToDeviceRegisterFragment()
        v.findNavController().navigate(action)
    }

    private val clickListenerDevices = View.OnClickListener {
        val action = TempMenuFragmentDirections.actionTempMenuFragmentToHomeFragment()
        v.findNavController().navigate(action)
    }

}