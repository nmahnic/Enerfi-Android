package com.nicomahnic.enerfiv2.ui.mainFragments.deviceregister

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.nicomahnic.enerfiv2.R
import com.nicomahnic.enerfiv2.databinding.FragmentDeviceRegisterBinding
import com.nicomahnic.enerfiv2.utils.core.BaseFragment

class DeviceRegisterFragment : BaseFragment<DeviceRegisterDataState, DeviceRegisterAction, DeviceRegisterEvent, DeviceRegisterVM>
    (R.layout.fragment_device_register) 
{
    override val viewModel: DeviceRegisterVM by viewModels()
    private lateinit var binding: FragmentDeviceRegisterBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDeviceRegisterBinding.bind(view)
    }

    override fun renderViewState(viewState: DeviceRegisterDataState) {
//        TODO("Not yet implemented")
    }

    override fun renderViewEffect(viewEffect: DeviceRegisterAction) {
//        TODO("Not yet implemented")
    }
}