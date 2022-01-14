package com.nicomahnic.enerfiv2.ui.mainFragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.nicomahnic.enerfiv2.R

class BlankFragment : Fragment(R.layout.fragment_blank) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val action = BlankFragmentDirections.actionBlankFragmentToHomeFragment()
//        view.findNavController().navigate(action)
    }

}