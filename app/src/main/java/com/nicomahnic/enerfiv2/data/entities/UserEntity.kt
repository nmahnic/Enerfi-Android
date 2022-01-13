package com.nicomahnic.enerfiv2.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userEntity")
data class UserEntity(
//    @PrimaryKey(autoGenerate = true)
//    val id: Int,
//    val password: String,
    @PrimaryKey
    val mail: String

)
