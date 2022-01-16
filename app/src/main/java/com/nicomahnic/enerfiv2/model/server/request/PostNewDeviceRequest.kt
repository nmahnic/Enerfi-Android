package com.nicomahnic.enerfiv2.model.server.request

data class PostNewDeviceRequest(
    val deviceName: String,
    val macAddress: String,
    val mail: String,
    val passwd: String,
)
