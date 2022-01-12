package com.nicomahnic.enerfiv2.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nicomahnic.enerfiv2.data.entities.VoltageEntity

@Dao
interface VoltageDao {

    @Query("SELECT * FROM voltageEntity")
    suspend fun getAllVoltageValues(): List<VoltageEntity>

    @Insert
    suspend fun insertVoltageValue(voltage: VoltageEntity)

}