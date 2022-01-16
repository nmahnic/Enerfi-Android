package com.nicomahnic.enerfiv2.ui.mainFragments.home

import com.nicomahnic.enerfiv2.model.server.response.PostDevicesByEmailResponse

// STATE
data class HomeDataState(
    val exception: Exception? = null,
    val data: List<PostDevicesByEmailResponse>? = null,
    val state: HomeState
)

//VIEW EFFECT
sealed class HomeAction {
    object OK: HomeAction()
}

// VIEW EVENT
sealed class HomeEvent {
    data class GetDevices(val mail: String, val passwd: String): HomeEvent()
}

// VIEW STATE
sealed class HomeState {
    object Initial : HomeState()
    object Devices : HomeState()
}