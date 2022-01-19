package com.nicomahnic.enerfiv2.repository.server

import android.util.Log
import com.nicomahnic.enerfiv2.apis.apiServer.PostDevicesByEmailResponseMapper
import com.nicomahnic.enerfiv2.apis.apiServer.PostUserRequestMapper
import com.nicomahnic.enerfiv2.apis.apiServer.ServerHelper
import com.nicomahnic.enerfiv2.model.server.request.PostUserRequest
import com.nicomahnic.enerfiv2.model.server.response.DevicesByEmailResponse
import com.nicomahnic.enerfiv2.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class PostDevicesByEmail @Inject constructor(
    private val apiHelper: ServerHelper,
    private val txnResponseMapper: PostDevicesByEmailResponseMapper,
    private val txnRequestMapper: PostUserRequestMapper
) {

    suspend fun request(req: PostUserRequest): Flow<DataState<List<DevicesByEmailResponse>>> = flow {

        try{

            Log.d("NM", "postDevicesByEmailRequest REQUEST")
            val res = apiHelper.postDevicesByEmailRequest(txnRequestMapper.mapToEntity(req))
            Log.d("NM", "postDevicesByEmailRequest Response")

            emit(DataState.Success(txnResponseMapper.mapFromEntityList(res)))
        } catch (e: Exception){
            Log.d("NM","postDevicesByEmailRequest REQUEST -> FAILURE ${e}")
            emit(DataState.Failure(e))
        }
    }

}