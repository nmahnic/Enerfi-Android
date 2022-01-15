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
                                    viewEffect = DeviceRegisterAction.SetOK_GetNetworks(res.data.macAddress)
                                    viewState = viewState.copy(
                                        data = res.data.networks,
                                        state = DeviceRegisterState.Scanned
                                    )
                                }
                                is DataState.Failure -> {
                                    viewEffect = DeviceRegisterAction.SetFAIL_GetNetworks
                                }
                            }
                        }.launchIn(viewModelScope)
                }
            }
            is DeviceRegisterEvent.SaveCredentials -> {
                Log.d("NM","event: SaveCredentials -> SSID:${viewEvent.ssid} PASSWD:${viewEvent.passwd}")
                viewModelScope.launch {
                    setCredentials.request(viewEvent.ssid,viewEvent.passwd)
                        .catch { exception -> Log.d("NM", "exception -> $exception") }
                        .onEach { res ->
                            when (res) {
                                is DataState.Success -> {
                                    viewEffect = DeviceRegisterAction.SetOK_SaveCredentials(res.data.macAddress.toString())
                                    viewState = viewState.copy(
                                        state = DeviceRegisterState.Connected
                                    )
                                }
                                is DataState.Failure -> {
                                    viewEffect = DeviceRegisterAction.SetFAIL_SaveCredentials
                                }
                            }
                        }.launchIn(viewModelScope)
                }
            }
            is DeviceRegisterEvent.SetNewDevice -> {
                Log.d("NM","event: SetNewDevice -> dato1:${viewEvent.dato}")
                viewModelScope.launch {
                    setNewDevice.request(PostNewDeviceRequest(viewEvent.dato))
                        .catch { exception -> Log.d("NM", "exception -> $exception") }
                        .onEach { res ->
                            when (res) {
                                is DataState.Success -> {

                                }
                                is DataState.Failure -> {

                                }
                            }
                        }.launchIn(viewModelScope)
                }
            }
        }
    }

}

 