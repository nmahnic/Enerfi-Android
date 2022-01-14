package com.nicomahnic.enerfiv2.ui.mainFragments.rvdevices

import com.github.mikephil.charting.data.Entry
import com.nicomahnic.enerfiv2.model.Voltage

// STATE
data class RvDevicesDataState(
    val exception: Exception? = null,
    val data: List<Entry>? = null,
    val state: RvDevicesState
)

//VIEW EFFECT
sealed class RvDevicesAction {
    object OK: RvDevicesAction()
}

// VIEW EVENT
sealed class RvDevicesEvent {
    object LoadData: RvDevicesEvent()
}

// VIEW STATE
sealed class RvDevicesState {
    object Initial : RvDevicesState()
}