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
import com.nicomahnic.enerfiv2.model.server.response.DevicesByEmailResponse
import com.nicomahnic.enerfiv2.utils.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeDataState, HomeAction, HomeEvent, HomeVM>
    (R.layout.fragment_home)
{

    override val viewModel: HomeVM by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var v: View
    private lateinit var mail: String
    private lateinit var passwd: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        v = view

        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        mail = prefs.getString("userMail","")!!
        passwd = prefs.getString("password","")!!

        viewModel.process(HomeEvent.GetDevices(mail,passwd))

    }

    private fun initReycleView(devices: List<DevicesByEmailResponse>){
        val recyclerView = binding.rvDevices
        recyclerView.layoutManager = LinearLayoutManager(context)
        ItemListener.mail = mail
        ItemListener.passwd = passwd
        ItemListener.viewModel = viewModel
        recyclerView.adapter = DeviceAdapter(devices, ItemListener)
    }

    object ItemListener: DeviceAdapter.OnBookClickListener {
        lateinit var mail: String
        lateinit var passwd: String
        lateinit var viewModel: HomeVM

        override fun onItemClick(position: Int, device: DevicesByEmailResponse) {
            val action = HomeFragmentDirections.actionHomeFragmentToMeasureFragment(
                mac = device.mac, name = device.name, mail = mail, passwd = passwd
            )
            viewModel.process(HomeEvent.Navigate(action))
        }

        override fun onDeleteItemClick(position: Int, device: DevicesByEmailResponse) {
            viewModel.process(HomeEvent.DeleteItem(mail, passwd, device))
        }

    }

    override fun renderViewState(viewState: HomeDataState) {
        when(viewState.state){
            is HomeState.Devices -> {
                initReycleView(viewState.data!!)
            }
            is HomeState.NavigateTo -> {
                v.findNavController().navigate(viewState.action!!)
            }
            is HomeState.ItemDeleted -> {
                viewModel.process(HomeEvent.GetDevices(mail,passwd))
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