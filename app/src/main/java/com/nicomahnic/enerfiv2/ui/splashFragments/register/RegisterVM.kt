package com.nicomahnic.enerfiv2.ui.splashFragments.register

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.nicomahnic.enerfiv2.model.local.User
import com.nicomahnic.enerfiv2.model.server.request.PostNewUserRequest
import com.nicomahnic.enerfiv2.repository.local.GetUsers
import com.nicomahnic.enerfiv2.repository.local.InsertUser
import com.nicomahnic.enerfiv2.repository.server.PostNewUser
import com.nicomahnic.enerfiv2.utils.DataState
import com.nicomahnic.enerfiv2.utils.core.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class RegisterVM @ViewModelInject constructor(
    private val getUsers: GetUsers,
    private val insertUser: InsertUser,
    private val postNewUser: PostNewUser
) : BaseViewModel<RegisterDataState, RegisterAction, RegisterEvent>(){

    init {
        viewState = RegisterDataState(
            state = RegisterState.Initial
        )
    }

    override fun process(viewEvent: RegisterEvent) {
        super.process(viewEvent)
        when (viewEvent){
            is RegisterEvent.Validate -> {
                if (viewEvent.mail.isNotBlank() && viewEvent.passwd == viewEvent.verifyPasswd && viewEvent.passwd.isNotBlank()){
                    postNewUser(viewEvent.name, viewEvent.mail, viewEvent.passwd)
                }else{
                    viewState = viewState.copy(state = RegisterState.NotValidated)
                }
            }
            is RegisterEvent.Register -> {
                insetUser(viewEvent.mail, viewEvent.passwd)
            }
        }
    }

    private fun postNewUser(name: String, mail: String, passwd: String){
        viewModelScope.launch {
            postNewUser.request(PostNewUserRequest(name, mail, passwd))
                .catch { exception -> Log.d("NM", "exception -> ${exception}")}
                .onEach { res ->
                    when (res) {
                        is DataState.Success -> {
                            Log.d("NM", "postNewUser SUCCESS: $res")
                            viewState = if(res.data.responseCode == 201){
                                viewState.copy(state = RegisterState.Validated, mail = mail, passwd = passwd)
                            } else {
                                viewState.copy(state = RegisterState.NotValidated)
                            }
                        }
                        is DataState.Failure -> {
                            Log.d("NM", "postNewUser FAIL: $res")
                            viewState = viewState.copy(state = RegisterState.FailureServer)
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    private fun insetUser(mail: String, passwd: String){
        viewModelScope.launch {
            insertUser.task(User(mail))
                .catch { e -> Log.d("NM", "insertUser Exception: $e") }
                .onEach { res ->
                    when(res){
                        is DataState.Success -> {
                            Log.d("NM", "insertUser Success: ${res.data}")
                            viewState = viewState.copy(
                                state = RegisterState.Registered
                            )
                        }
                        is DataState.Failure -> {
                            Log.d("NM", "insertUser Failure: ${res.exception}")
                            viewState = viewState.copy(
                                state = RegisterState.NotValidated
                            )
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    private fun getUser() {
        viewModelScope.launch {
            getUsers.task()
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