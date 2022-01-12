package com.nicomahnic.enerfiv2.ui.splashFragments.register

import com.github.mikephil.charting.data.Entry

// STATE
data class RegisterDataState(
    val exception: Exception? = null,
    val data: List<Entry>? = null,
    val state: RegisterState
)

//VIEW EFFECT
sealed class RegisterAction {
    object OK: RegisterAction()
}

// VIEW EVENT
sealed class RegisterEvent {
    object LoadData: RegisterEvent()
    data class AddPoint(val mail: String, val passwd: String): RegisterEvent()
}

// VIEW STATE
sealed class RegisterState {
    object Initial : RegisterState()
    object Plot : RegisterState()
    object AddPointToPlot : RegisterState()
}