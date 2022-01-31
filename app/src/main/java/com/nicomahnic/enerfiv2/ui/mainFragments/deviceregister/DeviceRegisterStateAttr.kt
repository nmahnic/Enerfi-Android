package com.nicomahnic.enerfiv2.ui.mainFragments.deviceregister

import java.lang.Exception

// STATE
data class DeviceRegisterDataState(
    val exception: Exception? = null,
    val mac: String? = null,
    val data: List<String>? = null,
    val state: DeviceRegisterState
)

//VIEW EFFECT
sealed class DeviceRegisterAction {
    data class OkGetNetworks(val macAddress: String): DeviceRegisterAction()
    object FailGetNetworks: DeviceRegisterAction()
    data class OkSaveCredentials(val macAddress: String): DeviceRegisterAction()
    object FailSaveCredentials: DeviceRegisterAction()
    object LoadingOff: DeviceRegisterAction()
}

// VIEW EVENT
sealed class DeviceRegisterEvent {
    object ScanWiFi: DeviceRegisterEvent()
    data class SaveCredentials(val ssid: String, val passwd: String): DeviceRegisterEvent()
    data class SetNewDevice(val deviceName: String , val mac: String , val mail: String , val passwd: String): DeviceRegisterEvent()
}

// VIEW STATE
sealed class DeviceRegisterState {
    object Initial: DeviceRegisterState()
    object Scanned: DeviceRegisterState()
    object Connected: DeviceRegisterState()
}