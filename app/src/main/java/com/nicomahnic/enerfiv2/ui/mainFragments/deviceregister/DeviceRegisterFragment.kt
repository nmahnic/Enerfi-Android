package com.nicomahnic.enerfiv2.ui.mainFragments.deviceregister

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import android.provider.Settings
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts.*
import com.nicomahnic.enerfiv2.R
import com.nicomahnic.enerfiv2.databinding.FragmentDeviceRegisterBinding
import com.nicomahnic.enerfiv2.utils.Utils
import com.nicomahnic.enerfiv2.utils.core.BaseFragment


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DeviceRegisterFragment : BaseFragment<DeviceRegisterDataState, DeviceRegisterAction, DeviceRegisterEvent, DeviceRegisterVM>(
    R.layout.fragment_device_register
) {

    companion object{
        fun newInstance(): DeviceRegisterFragment = DeviceRegisterFragment()
    }

    override val viewModel: DeviceRegisterVM by viewModels()
    private lateinit var binding: FragmentDeviceRegisterBinding

    private val responseLauncher = registerForActivityResult(StartActivityForResult()){
        viewModel.process(DeviceRegisterEvent.ScanWiFi)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDeviceRegisterBinding.bind(view)

        binding.btnScanNetworks.setOnClickListener{
            responseLauncher.launch(Intent(Settings.ACTION_WIFI_SETTINGS))
        }

        binding.btnSaveCredentials.setOnClickListener{
            val ssid =  binding.edtUser.text
            val passwd =  binding.edtPasswd.text
            if(ssid != null && passwd != null) {
                viewModel.process(
                    DeviceRegisterEvent.SaveCredentials(
                        ssid.toString(),
                        passwd.toString()
                    )
                )
            }
        }
    }

    override fun renderViewState(viewState: DeviceRegisterDataState) {
        when(viewState.state){
            is DeviceRegisterState.Scanned -> {
                binding.spinner.let{
                    val adapter = ArrayAdapter(requireContext(),
                        android.R.layout.simple_spinner_item, viewState.data!!)
                    binding.spinner.adapter = adapter
                }
            }
            is DeviceRegisterState.Connected -> {
                Utils.checkForInternet(requireContext())
                Handler().postDelayed({
                    viewModel.process(
                        DeviceRegisterEvent.SetNewDevice("NUEVO DISP")
                    )
                }, 10000)
            }
            else -> {
                Log.d("NM", "DeviceRegister VIEW STATE -> NOT DEFINED")
            }
        }
    }

    override fun renderViewEffect(viewEffect: DeviceRegisterAction) {
        Log.i("NM", "renderAction: $viewEffect")
        when(viewEffect){
            is DeviceRegisterAction.SetOK_GetNetworks -> {
                Log.d("NM", "Fragment Action -> ScannSuccess")
                binding.tvScannSuccess.visibility = View.VISIBLE
                binding.tvMacAddress.text = viewEffect.macAddress
            }
            is DeviceRegisterAction.SetFAIL_GetNetworks -> {
                Log.d("NM", "Fragment Action -> ScanNetworksFail")
                binding.tvScanNetworksFail.visibility = View.VISIBLE
            }
            is DeviceRegisterAction.SetOK_SaveCredentials -> {
                Log.d("NM", "Fragment Action -> SaveCredentialsSuccess")
                binding.tvSaveCredentialsSuccess.visibility = View.VISIBLE
                binding.tvMacAddress.text = viewEffect.macAddress
            }
            is DeviceRegisterAction.SetFAIL_SaveCredentials -> {
                Log.d("NM", "Fragment Action -> SaveCredentialsFail")
                binding.tvSaveCredentialsFail.visibility = View.VISIBLE
            }
        }
    }

}