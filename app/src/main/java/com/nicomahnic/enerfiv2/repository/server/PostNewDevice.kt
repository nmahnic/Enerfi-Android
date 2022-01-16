package com.nicomahnic.enerfiv2.repository.server

import android.util.Log
import com.nicomahnic.enerfiv2.apis.apiServer.PostGeneralResponseMapper
import com.nicomahnic.enerfiv2.apis.apiServer.PostNewDeviceRequestMapper
import com.nicomahnic.enerfiv2.apis.apiServer.ServerHelper
import com.nicomahnic.enerfiv2.model.server.request.PostNewDeviceRequest
import com.nicomahnic.enerfiv2.model.server.response.PostGeneralResponse
import com.nicomahnic.enerfiv2.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class PostNewDevice @Inject constructor(
    private val apiHelper: ServerHelper,
    private val txnResponseMapper: PostGeneralResponseMapper,
    private val txnRequestMapper: PostNewDeviceRequestMapper
) {

    suspend fun request(req: PostNewDeviceRequest): Flow<DataState<PostGeneralResponse>> = flow {

        try {
            Log.d("NM", "PostNewDevice Request -> ${req}")
            val res = apiHelper.postDeviceRequest(txnRequestMapper.mapToEntity(req))
            Log.d("NM", "PostNewDevice Response  -> message=${res.message}")
            Log.d("NM", "PostNewDevice Response  -> responseCode=${res.responseCode}")

            emit(DataState.Success(txnResponseMapper.mapFromEntity(res)))
        } catch (e: Exception) {
            Log.d("NM", "PostNewDevice Request -> FAILURE ${e}")
            emit(DataState.Failure(e))
        }
    }

}