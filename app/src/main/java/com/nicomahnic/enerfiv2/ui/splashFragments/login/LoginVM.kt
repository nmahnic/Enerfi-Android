package com.nicomahnic.enerfiv2.ui.splashFragments.login

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.nicomahnic.enerfiv2.repository.local.GetUsersByEmail
import com.nicomahnic.enerfiv2.utils.DataState
import com.nicomahnic.enerfiv2.utils.core.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class LoginVM @ViewModelInject constructor(
    private val getUsersByEmail: GetUsersByEmail
) : BaseViewModel<LoginDataState, LoginAction, LoginEvent>(){

    init {
        viewState = LoginDataState(
            state = LoginState.Initial
        )
    }

    override fun process(viewEvent: LoginEvent) {
        super.process(viewEvent)
        when (viewEvent){
            is LoginEvent.Validate -> {
                getUsersByEmail(viewEvent.mail)
            }
            is LoginEvent.Register -> {
                viewState = viewState.copy(
                    state = LoginState.GoToRegister
                )
            }
            is LoginEvent.GoToHome -> {
                viewState = viewState.copy(
                    state = LoginState.GoToHome
                )
            }
        }
    }
    

    private fun getUsersByEmail(mail : String) {
        viewModelScope.launch {
            getUsersByEmail.task(mail)
                .catch { e -> Log.d("NM", "getUser Exception: $e") }
                .onEach { res ->
                    when(res){
                        is DataState.Success -> {
                            Log.d("NM", "getUsersByEmail Success: ${res.data}")
                            viewState = if(res.data.isNotEmpty()){
                                viewState.copy(state = LoginState.Validated)
                            }else{
                                viewState.copy(state = LoginState.NotValidated)
                            }
                        }
                        is DataState.Failure -> {
                            Log.d("NM", "getUsersByEmail Failure: ${res.exception}")
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

}