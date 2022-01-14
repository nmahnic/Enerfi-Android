package com.nicomahnic.enerfiv2.ui.mainFragments.deviceregister

import java.lang.Exception

// STATE
data class DeviceRegisterDataState(
    val exception: Exception? = null,
    val data: List<String>? = null,
    val state: DeviceRegisterState
)

//VIEW EFFECT
sealed class DeviceRegisterAction {
    data class SetOK_GetNetworks(val macAddress: String): DeviceRegisterAction()
    object SetFAIL_GetNetworks: DeviceRegisterAction()
    data class SetOK_SaveCredentials(val macAddress: String): DeviceRegisterAction()
    object SetFAIL_SaveCredentials: DeviceRegisterAction()
}

// VIEW EVENT
sealed class DeviceRegisterEvent {
    object ScanWiFi: DeviceRegisterEvent()
    data class SaveCredentials(val ssid: String, val passwd: String): DeviceRegisterEvent()
    data class SetNewDevice(val dato: String): DeviceRegisterEvent()
}

// VIEW STATE
sealed class DeviceRegisterState {
    object Initial: DeviceRegisterState()
    object Scanned: DeviceRegisterState()
    object Connected: DeviceRegisterState()
}