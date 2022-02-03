package com.nicomahnic.enerfiv2.ui.mainFragments.home

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.nicomahnic.enerfiv2.model.server.request.PostUserAndDumRequest
import com.nicomahnic.enerfiv2.model.server.request.PostUserRequest
import com.nicomahnic.enerfiv2.model.server.response.DevicesByEmailResponse
import com.nicomahnic.enerfiv2.repository.server.PostDeleteDeviceByEmail
import com.nicomahnic.enerfiv2.repository.server.PostDevicesByEmail
import com.nicomahnic.enerfiv2.utils.DataState
import com.nicomahnic.enerfiv2.utils.core.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeVM @ViewModelInject constructor(
    private val postDevicesByEmail: PostDevicesByEmail,
    private val postDeleteDeviceByEmail: PostDeleteDeviceByEmail
) : BaseViewModel<HomeDataState, HomeAction, HomeEvent>(){

    init {
        viewState = HomeDataState(
            state = HomeState.Initial
        )
    }

    override fun process(viewEvent: HomeEvent) {
        super.process(viewEvent)
        when (viewEvent){
            is HomeEvent.GetDevices -> {
                getDevicesByEmailRemote(viewEvent.mail, viewEvent.passwd)
            }
            is HomeEvent.Navigate -> {
                viewState = viewState.copy(
                    state = HomeState.NavigateTo,
                    action = viewEvent.action
                )
            }
            is HomeEvent.DeleteItem -> {
                deleteItem(viewEvent.mail, viewEvent.passwd, viewEvent.device)
            }
        }
    }

    private fun deleteItem(mail: String, passwd: String, device: DevicesByEmailResponse) {
        viewModelScope.launch {
            postDeleteDeviceByEmail.request(PostUserAndDumRequest(mail, passwd, device.mac))
                .catch { e -> Log.d("NM", "postDeleteDeviceByEmail Exception: $e") }
                .onEach { res ->
                    when(res){
                        is DataState.Success -> {
                            Log.d("NM", "postDeleteDeviceByEmail Success: ${res.data}")
                        }
                        is DataState.Failure -> {
                            Log.d("NM", "postDeleteDeviceByEmail Failure: ${res.exception}")
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    private fun getDevicesByEmailRemote(mail: String, passwd: String) {
        viewModelScope.launch {
            postDevicesByEmail.request(PostUserRequest(mail, passwd))
                .catch { e -> Log.d("NM", "checkUsersByEmailRemote Exception: $e") }
                .onEach { res ->
                    when(res){
                        is DataState.Success -> {
                            Log.d("NM", "postDevicesByEmail Success: ${res.data}")
                            viewState = viewState.copy(
                                state = HomeState.Devices,
                                data = res.data
                            )
                        }
                        is DataState.Failure -> {
                            Log.d("NM", "postDevicesByEmail Failure: ${res.exception}")
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

}