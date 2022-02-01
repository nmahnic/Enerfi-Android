package com.nicomahnic.enerfiv2.ui.mainFragments.deviceregister

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.nicomahnic.enerfiv2.model.server.request.PostNewDeviceRequest
import com.nicomahnic.enerfiv2.repository.esp.GetNetworks
import com.nicomahnic.enerfiv2.repository.server.PostNewDevice
import com.nicomahnic.enerfiv2.repository.esp.SetCredentials
import com.nicomahnic.enerfiv2.utils.DataState
import com.nicomahnic.enerfiv2.utils.core.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DeviceRegisterVM @ViewModelInject constructor(
    private val getNetworks: GetNetworks,
    private val setCredentials: SetCredentials,
    private val setNewDevice: PostNewDevice
) : BaseViewModel<DeviceRegisterDataState, DeviceRegisterAction, DeviceRegisterEvent>() {


    init {
        viewState = DeviceRegisterDataState(
            state = DeviceRegisterState.Initial
        )
    }

    override fun process(viewEvent: DeviceRegisterEvent) {
        super.process(viewEvent)
        when (viewEvent) {
            is DeviceRegisterEvent.ScanWiFi -> {
                viewModelScope.launch {
                    getNetworks.request()
                        .catch { exception -> Log.d("NM", "exception -> $exception") }
                        .onEach { res ->
                            when (res) {
                                is DataState.Success -> {
                                    viewEffect = DeviceRegisterAction.OkGetNetworks(res.data.macAddress)
                                    viewState = viewState.copy(
                                        data = res.data.networks,
                                        state = DeviceRegisterState.Scanned
                                    )
                                }
                                is DataState.Failure -> {
                                    viewEffect = DeviceRegisterAction.FailGetNetworks
                                }
                            }
                        }.launchIn(viewModelScope)
                }
            }
            is DeviceRegisterEvent.SaveCredentials -> {
                Log.d("NM","event: SaveCredentials -> SSID:${viewEvent.ssid} PASSWD:${viewEvent.passwd}")
                setCredentials(viewEvent.ssid, viewEvent.passwd)
            }
            is DeviceRegisterEvent.SetNewDevice -> {
                Log.d("NM","event: SetNewDevice -> deviceName:${viewEvent.deviceName}")
                Log.d("NM","event: SetNewDevice -> mac:${viewEvent.mac}")
                Log.d("NM","event: SetNewDevice -> mail:${viewEvent.mail}")
                Log.d("NM","event: SetNewDevice -> passwd:${viewEvent.passwd}")
                setNewDevice(viewEvent.deviceName, viewEvent.mac, viewEvent.mail, viewEvent.passwd)
            }
        }
    }

    private fun setCredentials(ssid : String, passwd: String){
        viewModelScope.launch {
            setCredentials.request(ssid, passwd)
                .catch { exception -> Log.d("NM", "exception -> $exception") }
                .onEach { res ->
                    when (res) {
                        is DataState.Success -> {
                            Log.d("NM", "setCredentials Success: ${res.data}")
                            viewEffect = DeviceRegisterAction.OkSaveCredentials(res.data.macAddress)
                            viewState = viewState.copy(
                                state = DeviceRegisterState.Connected,
                                mac = res.data.macAddress
                            )
                        }
                        is DataState.Failure -> {
                            Log.d("NM", "setCredentials Failure: ${res.exception}")
                            viewEffect = DeviceRegisterAction.FailSaveCredentials
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    private fun setNewDevice(deviceName: String, mac: String, mail: String, passwd: String){
        viewModelScope.launch {
            setNewDevice.request(PostNewDeviceRequest(deviceName, mac, mail, passwd))
                .catch { exception -> Log.d("NM", "exception -> $exception") }
                .onEach { res ->
                    when (res) {
                        is DataState.Success -> {
                            Log.d("NM", "setNewDevice Success: ${res.data}")
                            viewEffect = DeviceRegisterAction.LoadingOff
                        }
                        is DataState.Failure -> {
                            Log.d("NM", "setNewDevice Failure: ${res.exception}")
                            viewEffect = DeviceRegisterAction.ErrorOn
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

}

 