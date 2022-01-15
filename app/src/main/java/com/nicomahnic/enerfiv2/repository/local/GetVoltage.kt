package com.nicomahnic.enerfiv2.repository.local

import android.util.Log
import com.nicomahnic.enerfiv2.data.entities.VoltageMapper
import com.nicomahnic.enerfiv2.domain.VoltageDao
import com.nicomahnic.enerfiv2.model.local.Voltage
import com.nicomahnic.enerfiv2.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject


class GetVoltage @Inject constructor(
    private val voltageDao: VoltageDao,
    private val voltageMapper: VoltageMapper
) {

    suspend fun task() : Flow<DataState<List<Voltage>>> = flow {
        Log.d("NM", "getVoltage llegue")
        try {
            val res = mutableListOf<Voltage>()
            val resEntity = voltageDao.getAllVoltageValues()
            resEntity.forEach { res.add(voltageMapper.mapFromEntity(it)) }
            emit(DataState.Success(res))
        } catch (e: Exception){
            emit(DataState.Failure(e))
        }
    }

}