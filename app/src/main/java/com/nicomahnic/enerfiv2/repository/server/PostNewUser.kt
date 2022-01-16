package com.nicomahnic.enerfiv2.repository.server

import android.util.Log
import com.nicomahnic.enerfiv2.apis.apiServer.PostNewUserRequestMapper
import com.nicomahnic.enerfiv2.apis.apiServer.PostGeneralResponseMapper
import com.nicomahnic.enerfiv2.apis.apiServer.ServerHelper
import com.nicomahnic.enerfiv2.model.server.request.PostNewUserRequest
import com.nicomahnic.enerfiv2.model.server.response.PostGeneralResponse
import com.nicomahnic.enerfiv2.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class PostNewUser @Inject constructor(
    private val apiHelper: ServerHelper,
    private val txnResponseMapper: PostGeneralResponseMapper,
    private val txnRequestMapper: PostNewUserRequestMapper
) {

    suspend fun request(req: PostNewUserRequest): Flow<DataState<PostGeneralResponse>> = flow {

        try {
            Log.d("NM", "PostNewUser REQUEST")
            val res = apiHelper.postNewUserRequest(txnRequestMapper.mapToEntity(req))
            Log.d("NM", "PostNewUser Response  -> message=${res.message}")
            Log.d("NM", "PostNewUser Response  -> responseCode=${res.responseCode}")

            emit(DataState.Success(txnResponseMapper.mapFromEntity(res)))
        } catch (e: Exception) {
            Log.d("NM", "PostNewUser REQUEST -> FAILURE ${e}")
            emit(DataState.Failure(e))
        }
    }

}