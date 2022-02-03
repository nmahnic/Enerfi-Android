package com.nicomahnic.enerfiv2.ui.mainFragments.home

import androidx.navigation.NavDirections
import com.nicomahnic.enerfiv2.model.server.response.DevicesByEmailResponse

// STATE
data class HomeDataState(
    val exception: Exception? = null,
    val action: NavDirections? = null,
    val data: List<DevicesByEmailResponse>? = null,
    val state: HomeState
)

//VIEW EFFECT
sealed class HomeAction {
    object OK: HomeAction()
}

// VIEW EVENT
sealed class HomeEvent {
    data class GetDevices(val mail: String, val passwd: String): HomeEvent()
    data class Navigate(val action: NavDirections): HomeEvent()
    data class DeleteItem(val mail: String, val passwd: String, val device: DevicesByEmailResponse): HomeEvent()
}

// VIEW STATE
sealed class HomeState {
    object Initial : HomeState()
    object Devices : HomeState()
    object NavigateTo : HomeState()
    object ItemDeleted : HomeState()
}