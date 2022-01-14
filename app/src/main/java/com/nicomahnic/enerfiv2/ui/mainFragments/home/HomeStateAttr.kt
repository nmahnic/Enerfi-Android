package com.nicomahnic.enerfiv2.ui.mainFragments.home

import android.content.SharedPreferences
import com.github.mikephil.charting.data.Entry
import com.nicomahnic.enerfiv2.ui.splashFragments.register.RegisterEvent

// STATE
data class HomeDataState(
    val exception: Exception? = null,
    val mail: String? = null,
    val passwd: String? = null,
    val state: HomeState
)

//VIEW EFFECT
sealed class HomeAction {
    object OK: HomeAction()
}

// VIEW EVENT
sealed class HomeEvent {
    data class GetDevices(val mail: String): HomeEvent()
}

// VIEW STATE
sealed class HomeState {
    object Initial : HomeState()
}