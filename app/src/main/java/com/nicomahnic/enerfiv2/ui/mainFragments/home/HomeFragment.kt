package com.nicomahnic.enerfiv2.ui.mainFragments.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicomahnic.enerfiv2.R
import com.nicomahnic.enerfiv2.databinding.FragmentHomeBinding
import com.nicomahnic.enerfiv2.model.server.response.PostDevicesByEmailResponse
import com.nicomahnic.enerfiv2.utils.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeDataState, HomeAction, HomeEvent, HomeVM>
    (R.layout.fragment_home)
{

    override val viewModel: HomeVM by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var v: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        v = view

        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val mail = prefs.getString("userMail","")!!
        val passwd = prefs.getString("password","")!!

        viewModel.process(HomeEvent.GetDevices(mail,passwd))

    }

    private fun initReycleView(devices: List<PostDevicesByEmailResponse>){
        val recyclerView = binding.rvDevices
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = DeviceAdapter(devices) { pos, device -> onItemSelected(pos,device) }
    }

    private fun onItemSelected(pos: Int, device: PostDevicesByEmailResponse){
        val action = HomeFragmentDirections.actionHomeFragmentToMeasureFragment()
        v.findNavController().navigate(action)
    }

    override fun renderViewState(viewState: HomeDataState) {
        when(viewState.state){
            is HomeState.Devices -> {
                initReycleView(viewState.data!!)
            }
            else -> {
                Log.d("NM", "${viewState.state}")
            }
        }
    }

    override fun renderViewEffect(viewEffect: HomeAction) {
//        TODO("Not yet implemented")
    }
}