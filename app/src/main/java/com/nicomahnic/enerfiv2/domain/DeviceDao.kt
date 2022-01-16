package com.nicomahnic.enerfiv2.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nicomahnic.enerfiv2.data.entities.DeviceEntity

@Dao
interface DeviceDao {

    @Query("SELECT * FROM deviceEntity")
    suspend fun getAllDevices(): List<DeviceEntity>

    @Query("SELECT * FROM deviceEntity WHERE mail = :mail")
    suspend fun getDeviceByEmail(mail: String): List<DeviceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDevice(user: DeviceEntity)

}