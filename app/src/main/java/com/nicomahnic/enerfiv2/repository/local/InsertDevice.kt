package com.nicomahnic.enerfiv2.repository.local

import android.util.Log
import com.nicomahnic.enerfiv2.data.entities.DeviceMapper
import com.nicomahnic.enerfiv2.domain.DeviceDao
import com.nicomahnic.enerfiv2.model.local.Device
import com.nicomahnic.enerfiv2.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class InsertDevice @Inject constructor(
    private val deviceDao: DeviceDao,
    private val deviceMapper: DeviceMapper
) {

    suspend fun task(device: Device) : Flow<DataState<String>> = flow {
        Log.d("NM", "insertUser llegue")
        try {
            deviceDao.insertDevice(deviceMapper.mapToEntity(device))
            emit(DataState.Success("OK"))
        } catch (e: Exception){
            emit(DataState.Failure(e))
        }

    }

}