package com.nicomahnic.enerfiv2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nicomahnic.enerfiv2.data.entities.VoltageMapper
import com.nicomahnic.enerfiv2.domain.VoltageDao
import com.nicomahnic.enerfiv2.model.Voltage
import javax.inject.Inject


class GetVoltage @Inject constructor(
    private val voltageDao: VoltageDao,
    private val voltageMapper: VoltageMapper
) {

    suspend fun getVoltage() : List<Voltage>? {
        val res = mutableListOf<Voltage>()
        val resEntity = voltageDao.getAllVoltageValues()
        resEntity.forEach {
            res.add(voltageMapper.mapFromEntity(it))
        }
        return res
    }

}