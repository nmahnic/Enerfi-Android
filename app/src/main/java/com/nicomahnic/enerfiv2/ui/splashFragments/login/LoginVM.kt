package com.nicomahnic.enerfiv2.ui.splashFragments.login

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.nicomahnic.enerfiv2.repository.GetUsers
import com.nicomahnic.enerfiv2.utils.DataState
import com.nicomahnic.enerfiv2.utils.core.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class LoginVM @ViewModelInject constructor(
    private val getUsers: GetUsers
) : BaseViewModel<LoginDataState, LoginAction, LoginEvent>(){

    init {
        viewState = LoginDataState(
            state = LoginState.Initial
        )
    }

    override fun process(viewEvent: LoginEvent) {
        super.process(viewEvent)
        when (viewEvent){
            is LoginEvent.LoadData -> {
                getUsers()
            }
        }
    }
    

    private fun getUsers() {
        viewModelScope.launch {
            getUsers.getUser()
                .catch { e -> Log.d("NM", "getUser Exception: $e") }
                .onEach { res ->
                    when(res){
                        is DataState.Success -> {
                            Log.d("NM", "getUser Success: ${res.data}")
                        }
                        is DataState.Failure -> {
                            Log.d("NM", "getUser Failure: ${res.exception}")
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

}