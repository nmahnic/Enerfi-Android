package com.nicomahnic.enerfiv2.ui.mainFragments.deviceregister

import com.github.mikephil.charting.data.Entry
import com.nicomahnic.enerfiv2.model.Voltage

// STATE
data class DeviceRegisterDataState(
    val exception: Exception? = null,
    val data: List<Entry>? = null,
    val state: DeviceRegisterState
)

//VIEW EFFECT
sealed class DeviceRegisterAction {
    object OK: DeviceRegisterAction()
}

// VIEW EVENT
sealed class DeviceRegisterEvent {
    object LoadData: DeviceRegisterEvent()
}

// VIEW STATE
sealed class DeviceRegisterState {
    object Initial : DeviceRegisterState()
}