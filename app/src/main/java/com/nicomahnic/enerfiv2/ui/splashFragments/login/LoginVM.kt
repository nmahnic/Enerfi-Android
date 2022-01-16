package com.nicomahnic.enerfiv2.ui.splashFragments.login

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.nicomahnic.enerfiv2.model.server.request.PostUserRequest
import com.nicomahnic.enerfiv2.repository.local.GetUsersByEmail
import com.nicomahnic.enerfiv2.repository.server.PostValidateUser
import com.nicomahnic.enerfiv2.utils.DataState
import com.nicomahnic.enerfiv2.utils.core.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class LoginVM @ViewModelInject constructor(
    private val getUsersByEmail: GetUsersByEmail,
    private val postValidateUser: PostValidateUser
) : BaseViewModel<LoginDataState, LoginAction, LoginEvent>(){

    init {
        viewState = LoginDataState( state = LoginState.Initial )
    }

    override fun process(viewEvent: LoginEvent) {
        super.process(viewEvent)
        when (viewEvent){
            is LoginEvent.Validate -> {
                checkUsersByEmailLocal(viewEvent.mail, viewEvent.passwd)
            }
            is LoginEvent.Register -> {
                viewState = viewState.copy( state = LoginState.GoToRegister )
            }
            is LoginEvent.GoToHome -> {
                viewState = viewState.copy( state = LoginState.GoToHome )
            }
        }
    }
    

    private fun checkUsersByEmailLocal(mail : String, passwd : String) {
        viewModelScope.launch {
            getUsersByEmail.task(mail)
                .catch { e -> Log.d("NM", "checkUsersByEmailLocal Exception: $e") }
                .onEach { res ->
                    when(res){
                        is DataState.Success -> {
                            Log.d("NM", "checkUsersByEmailLocal Success: ${res.data}")
                            checkUsersByEmailRemote(mail, passwd)
                        }
                        is DataState.Failure -> {
                            Log.d("NM", "checkUsersByEmailLocal Failure: ${res.exception}")
                            viewState = viewState.copy(state = LoginState.FailureServer)
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    private fun checkUsersByEmailRemote(mail : String, passwd : String) {
        viewModelScope.launch {
            postValidateUser.request(PostUserRequest(mail, passwd))
                .catch { e -> Log.d("NM", "checkUsersByEmailRemote Exception: $e") }
                .onEach { res ->
                    viewState = when(res){
                        is DataState.Success -> {
                            Log.d("NM", "checkUsersByEmailRemote Success: ${res.data}")
                            if(res.data.responseCode == 200){
                                viewState.copy(state = LoginState.Validated)
                            }else{
                                viewState.copy(state = LoginState.NotValidated)
                            }
                        }
                        is DataState.Failure -> {
                            Log.d("NM", "checkUsersByEmailRemote Failure: ${res.exception}")
                            viewState.copy(state = LoginState.FailureServer)
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

}