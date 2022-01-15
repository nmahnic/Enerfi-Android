package com.nicomahnic.enerfiv2.repository.server

import android.util.Log
import com.nicomahnic.enerfiv2.apis.apiServer.PostNewUserRequestMapper
import com.nicomahnic.enerfiv2.apis.apiServer.PostNewUserResponseMapper
import com.nicomahnic.enerfiv2.apis.apiServer.ServerHelper
import com.nicomahnic.enerfiv2.model.server.request.PostNewUserRequest
import com.nicomahnic.enerfiv2.model.server.response.PostNewUserResponse
import com.nicomahnic.enerfiv2.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class PostNewUser @Inject constructor(
    private val apiHelper: ServerHelper,
    private val txnResponseMapper: PostNewUserResponseMapper,
    private val txnRequestMapper: PostNewUserRequestMapper
) {

    suspend fun request(req: PostNewUserRequest): Flow<DataState<PostNewUserResponse>> = flow {

        try {
            Log.d("NM", "GET NETWORKS REQUEST")
            val res = apiHelper.postUserRequest(txnRequestMapper.mapToEntity(req))
            Log.d("NM", "Status Response  -> networks=${res.macAddress}")
            Log.d("NM", "Status Response  -> quality=${res.responseCode}")

            emit(DataState.Success(txnResponseMapper.mapFromEntity(res)))
        } catch (e: Exception) {
            Log.d("NM", "GET NETWORKS REQUEST -> FAILURE ${e}")
            emit(DataState.Failure(e))
        }
    }

}