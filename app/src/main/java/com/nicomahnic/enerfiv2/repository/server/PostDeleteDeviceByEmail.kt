package com.nicomahnic.enerfiv2.repository.server

import android.util.Log
import com.nicomahnic.enerfiv2.apis.apiServer.GeneralResponseMapper
import com.nicomahnic.enerfiv2.apis.apiServer.PostUserAndDumRequestMapper
import com.nicomahnic.enerfiv2.apis.apiServer.ServerHelper
import com.nicomahnic.enerfiv2.model.server.request.PostUserAndDumRequest
import com.nicomahnic.enerfiv2.model.server.response.GeneralResponse
import com.nicomahnic.enerfiv2.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class PostDeleteDeviceByEmail @Inject constructor(
    private val apiHelper: ServerHelper,
    private val txnResponseMapper: GeneralResponseMapper,
    private val txnRequestMapper: PostUserAndDumRequestMapper
) {

    suspend fun request(req: PostUserAndDumRequest): Flow<DataState<GeneralResponse>> = flow {

        try{
            Log.d("NM", "postDevicesByEmailRequest REQUEST")
            val res = apiHelper.postDeleteDeviceByEmailRequest(txnRequestMapper.mapToEntity(req))
            Log.d("NM", "postDevicesByEmailRequest Response")

            emit(DataState.Success(txnResponseMapper.mapFromEntity(res)))
        } catch (e: Exception){
            Log.d("NM","postDevicesByEmailRequest REQUEST -> FAILURE ${e}")
            emit(DataState.Failure(e))
        }
    }

}