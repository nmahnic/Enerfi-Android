package com.nicomahnic.enerfiv2.ui.mainFragments.rvdevices

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.nicomahnic.enerfiv2.R
import com.nicomahnic.enerfiv2.databinding.FragmentRvDevicesBinding
import com.nicomahnic.enerfiv2.utils.core.BaseFragment

class RvDevicesFragment : BaseFragment<RvDevicesDataState, RvDevicesAction, RvDevicesEvent, RvDevicesVM>
    (R.layout.fragment_device_register) 
{
    override val viewModel: RvDevicesVM by viewModels()
    private lateinit var binding: FragmentRvDevicesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRvDevicesBinding.bind(view)
    }

    override fun renderViewState(viewState: RvDevicesDataState) {
//        TODO("Not yet implemented")
    }

    override fun renderViewEffect(viewEffect: RvDevicesAction) {
//        TODO("Not yet implemented")
    }
}