package com.nicomahnic.enerfiv2.repository.server

import android.util.Log
import com.nicomahnic.enerfiv2.apis.apiServer.PostDevicesByEmailResponseMapper
import com.nicomahnic.enerfiv2.apis.apiServer.PostUserRequestMapper
import com.nicomahnic.enerfiv2.apis.apiServer.ServerHelper
import com.nicomahnic.enerfiv2.model.server.request.PostUserRequest
import com.nicomahnic.enerfiv2.model.server.response.PostDevicesByEmailResponse
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

    suspend fun request(req: PostUserRequest): Flow<DataState<List<PostDevicesByEmailResponse>>> = flow {

        try{
            val res = mutableListOf<PostDevicesByEmailResponse>()

            Log.d("NM", "postDevicesByEmailRequest REQUEST")
            val resEntity = apiHelper.postDevicesByEmailRequest(txnRequestMapper.mapToEntity(req))
            resEntity.forEach { res.add(txnResponseMapper.mapFromEntity(it)) }

            Log.d("NM", "postDevicesByEmailRequest  -> resEntity=${resEntity}")

            emit(DataState.Success(res))
        } catch (e: Exception){
            Log.d("NM","postDevicesByEmailRequest REQUEST -> FAILURE ${e}")
            emit(DataState.Failure(e))
        }
    }

}