package com.nicomahnic.enerfiv2.model.local

data class Device(
    val deviceName: String,
    val mac: String,
    val mail: String,
    val id: Int? = null
)
