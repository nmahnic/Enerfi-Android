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

class InsertVoltage @Inject constructor(
    private val voltageDao: VoltageDao,
    private val voltageMapper: VoltageMapper
) {

    suspend fun task(voltage: Voltage) : Flow<DataState<String>> = flow {
        Log.d("NM", "insertVoltage llegue")
        try {
            voltageDao.insertVoltageValue(voltageMapper.mapToEntity(voltage))
            emit(DataState.Success("OK"))
        } catch (e: Exception){
            emit(DataState.Failure(e))
        }

    }

}