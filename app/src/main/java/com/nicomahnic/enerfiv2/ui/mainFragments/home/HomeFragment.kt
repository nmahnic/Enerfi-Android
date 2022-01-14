package com.nicomahnic.enerfiv2.ui.mainFragments.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicomahnic.enerfiv2.R
import com.nicomahnic.enerfiv2.databinding.FragmentHomeBinding
import com.nicomahnic.enerfiv2.ui.mainFragments.home.model.Device
import com.nicomahnic.enerfiv2.ui.mainFragments.home.model.DevicesProvider
import com.nicomahnic.enerfiv2.utils.core.BaseFragment


class HomeFragment : BaseFragment<HomeDataState, HomeAction, HomeEvent, HomeVM>(R.layout.fragment_home) {

    override val viewModel: HomeVM by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var v: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        v = view

        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())

        initReycleView()

    }

    private fun initReycleView(){
        val recyclerView = binding.rvDevices
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = DeviceAdapter(DevicesProvider.devices) { pos,device -> onItemSelected(pos,device) }
    }

    private fun onItemSelected(pos: Int, device: Device){
        Toast.makeText(context,device.name,Toast.LENGTH_SHORT).show()
    }

    override fun renderViewState(viewState: HomeDataState) {
//        TODO("Not yet implemented")
    }

    override fun renderViewEffect(viewEffect: HomeAction) {
//        TODO("Not yet implemented")
    }
}