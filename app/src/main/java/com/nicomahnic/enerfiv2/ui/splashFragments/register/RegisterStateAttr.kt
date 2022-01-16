package com.nicomahnic.enerfiv2.ui.splashFragments.register

// STATE
data class RegisterDataState(
    val exception: Exception? = null,
    val mail: String? = null,
    val passwd: String? = null,
    val state: RegisterState
)

//VIEW EFFECT
sealed class RegisterAction {
    object OK: RegisterAction()
}

// VIEW EVENT
sealed class RegisterEvent {
    data class Register(val mail: String, val passwd: String): RegisterEvent()
    data class Validate(val name: String, val mail: String, val passwd: String, val verifyPasswd: String): RegisterEvent()
}

// VIEW STATE
sealed class RegisterState {
    object Initial : RegisterState()
    object Validated : RegisterState()
    object NotValidated : RegisterState()
    object Registered : RegisterState()
    object GoToHome : RegisterState()
    object FailureServer: RegisterState()
    object FailureLocal: RegisterState()
}