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
    object Register : LoginEvent()
    object GoToHome: LoginEvent()
    data class Validate(val mail: String, val passwd: String, val spMail: String, val spPasswd: String): LoginEvent()
}

// VIEW STATE
sealed class LoginState {
    object Initial : LoginState()
    object Validated : LoginState()
    object NotValidated : LoginState()
    object GoToRegister : LoginState()
    object GoToHome : LoginState()
    object FailureServer: LoginState()
    object FailureLocal: LoginState()
}