package com.nicomahnic.enerfiv2.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deviceEntity")
data class DeviceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val mail: String,
    val deviceName: String,
    val mac: String
)
