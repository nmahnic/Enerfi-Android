package com.nicomahnic.enerfiv2.ui.splashFragments.login

import com.github.mikephil.charting.data.Entry

// STATE
data class LoginDataState(
    val exception: Exception? = null,
    val data: List<Entry>? = null,
    val state: LoginState
)

//VIEW EFFECT
sealed class LoginAction {
    object OK: LoginAction()
}

// VIEW EVENT
sealed class LoginEvent {
    object LoadData: LoginEvent()
}

// VIEW STATE
sealed class LoginState {
    object Initial : LoginState()
    object Plot : LoginState()
}