package com.nicomahnic.enerfiv2.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "voltageEntity")
data class VoltageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val x: Float,
    val y: Float
)
