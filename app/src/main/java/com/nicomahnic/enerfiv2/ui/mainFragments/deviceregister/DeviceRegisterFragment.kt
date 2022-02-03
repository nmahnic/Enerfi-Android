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
import androidx.preference.PreferenceManager
import com.nicomahnic.enerfiv2.R
import com.nicomahnic.enerfiv2.databinding.FragmentDeviceRegisterBinding
import com.nicomahnic.enerfiv2.utils.Utils
import com.nicomahnic.enerfiv2.utils.core.BaseFragment


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DeviceRegisterFragment : BaseFragment<DeviceRegisterDataState, DeviceRegisterAction, DeviceRegisterEvent, DeviceRegisterVM>(
    R.layout.fragment_device_register
) {

    override val viewModel: DeviceRegisterVM by viewModels()
    private lateinit var binding: FragmentDeviceRegisterBinding
    private lateinit var ssid: String
    private lateinit var passwd: String
    private lateinit var mail: String

    private val responseLauncher = registerForActivityResult(StartActivityForResult()){
        viewModel.process(DeviceRegisterEvent.ScanWiFi)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDeviceRegisterBinding.bind(view)

        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        mail = prefs.getString("userMail", "")!!
        passwd = prefs.getString("password", "")!!

        binding.btnScanNetworks.setOnClickListener{
            binding.loadingScanCheck.visibility = View.VISIBLE
            responseLauncher.launch(Intent(Settings.ACTION_WIFI_SETTINGS))
        }

        binding.btnSaveCredentials.setOnClickListener{
            ssid =  binding.dropdownMenu.text.toString()
            binding.loadingCheck.visibility = View.VISIBLE
            val passwd =  binding.edtPassword.text.toString()
            if(ssid.isNotBlank() && passwd.isNotBlank()) {
                viewModel.process(DeviceRegisterEvent.SaveCredentials(ssid, passwd))
            }
        }
    }

    override fun renderViewState(viewState: DeviceRegisterDataState) {
        when(viewState.state){
            is DeviceRegisterState.Scanned -> {
                binding.dropdownMenu.let{
                    val adapter = ArrayAdapter(requireContext(),R.layout.item_dropdown, viewState.data!!)
                    binding.dropdownMenu.setAdapter(adapter)
                }
            }
            is DeviceRegisterState.ESPhasSavedCredentials -> {
                while(Utils.isOnline(requireContext())){
                    Log.d("NM", "INTERNET ${Utils.isOnline(requireContext())}")
                    Thread.sleep(1000)
                }
                Handler().postDelayed({
                    viewModel.process(
                        DeviceRegisterEvent.SetNewDevice(
                            deviceName = binding.edtDeviceName.text.toString(),
                            mac = viewState.mac!!,
                            passwd = passwd,
                            mail = mail
                        ))
                },1000)
            }
            else -> {
                Log.d("NM", "DeviceRegister VIEW STATE -> NOT DEFINED")
            }
        }
    }

    override fun renderViewEffect(viewEffect: DeviceRegisterAction) {
        when(viewEffect){
            is DeviceRegisterAction.OkGetNetworks -> {
                Log.d("NM", "Fragment Action -> ScannSuccess")
                binding.tvScanNetworks.visibility = View.VISIBLE
                binding.tvScanNetworks.text = resources.getString(R.string.success)
                binding.loadingScanCheck.visibility = View.INVISIBLE
                binding.credentials.visibility = View.VISIBLE
                binding.tvMacAddress.text = viewEffect.macAddress
            }
            is DeviceRegisterAction.FailGetNetworks -> {
                Log.d("NM", "Fragment Action -> ScanNetworksFail")
                binding.tvScanNetworks.visibility = View.VISIBLE
                binding.tvScanNetworks.text = resources.getString(R.string.fail)
            }
            is DeviceRegisterAction.OkSaveCredentials -> {
                Log.d("NM", "Fragment Action -> SaveCredentialsSuccess")
                binding.tvScanNetworks.text = resources.getString(R.string.loading)
                binding.loadingCheck.visibility = View.VISIBLE
            }
            is DeviceRegisterAction.FailSaveCredentials -> {
                Log.d("NM", "Fragment Action -> SaveCredentialsFail")
                binding.tvSaveCredentials.visibility = View.VISIBLE
                binding.tvScanNetworks.text = resources.getString(R.string.fail)
            }
            is DeviceRegisterAction.LoadingOff -> {
                binding.tvSaveCredentials.text = resources.getString(R.string.go_home)
//                binding.tvSaveCredentials.text = resources.getString(R.string.go_home)
                binding.loadingCheck.visibility = View.GONE
            }
            is DeviceRegisterAction.ErrorOn -> {
                Log.d("NM", "Fragment Action -> ErrorOn")
                binding.loadingCheck.visibility = View.GONE
                binding.errorMark.visibility = View.VISIBLE
            }
        }
    }

}