package com.nicomahnic.enerfiv2.repository

import com.nicomahnic.enerfiv2.data.database.AppDatabase
import com.nicomahnic.enerfiv2.data.entities.VoltageMapper
import com.nicomahnic.enerfiv2.domain.VoltageDao
import com.nicomahnic.enerfiv2.model.Voltage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class InsertVoltage @Inject constructor(
    private val voltageDao: VoltageDao,
    private val voltageMapper: VoltageMapper
) {

    suspend fun insertVoltage(voltage: Voltage){
        voltageDao.insertVoltageValue(voltageMapper.mapToEntity(voltage))
    }

}