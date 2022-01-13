package com.nicomahnic.enerfiv2.ui.splashFragments.register

import android.content.SharedPreferences
import com.github.mikephil.charting.data.Entry

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
    data class Validate(val mail: String, val passwd: String, val verifyPasswd: String, val pref: SharedPreferences): RegisterEvent()
}

// VIEW STATE
sealed class RegisterState {
    object Initial : RegisterState()
    object Validated : RegisterState()
    object NotValidated : RegisterState()
    object Registered : RegisterState()
    object GoToHome : RegisterState()
}