package com.nicomahnic.enerfiv2.ui.splashFragments.register

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.nicomahnic.enerfiv2.model.User
import com.nicomahnic.enerfiv2.repository.GetUsers
import com.nicomahnic.enerfiv2.repository.InsertUser
import com.nicomahnic.enerfiv2.utils.DataState
import com.nicomahnic.enerfiv2.utils.core.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class RegisterVM @ViewModelInject constructor(
    private val getUsers: GetUsers,
    private val insertUser: InsertUser,
) : BaseViewModel<RegisterDataState, RegisterAction, RegisterEvent>(){

    init {
        viewState = RegisterDataState(
            state = RegisterState.Initial
        )
    }

    override fun process(viewEvent: RegisterEvent) {
        super.process(viewEvent)
        when (viewEvent){
            is RegisterEvent.LoadData -> {
                getUser()
            }
            is RegisterEvent.AddPoint -> {
                insetUser(viewEvent.mail, viewEvent.passwd)
            }
        }
    }

    private fun insetUser(mail: String, passwd: String){
        viewModelScope.launch {
            insertUser.insertUser(User(mail, passwd))
                .catch { e -> Log.d("NM", "insertUser Exception: $e") }
                .onEach { res ->
                    when(res){
                        is DataState.Success -> {
                            Log.d("NM", "insertUser Success: ${res.data}")
                        }
                        is DataState.Failure -> {
                            Log.d("NM", "insertUser Failure: ${res.exception}")
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    private fun getUser() {
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