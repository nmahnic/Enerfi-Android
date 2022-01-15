package com.nicomahnic.enerfiv2.repository.server

import android.util.Log
import com.nicomahnic.enerfiv2.apis.apiServer.PostNewUserRequestMapper
import com.nicomahnic.enerfiv2.apis.apiServer.PostGeneralResponseMapper
import com.nicomahnic.enerfiv2.apis.apiServer.PostUserRequestMapper
import com.nicomahnic.enerfiv2.apis.apiServer.ServerHelper
import com.nicomahnic.enerfiv2.model.server.request.PostUserRequest
import com.nicomahnic.enerfiv2.model.server.response.PostGeneralResponse
import com.nicomahnic.enerfiv2.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class PostValidateUser @Inject constructor(
    private val apiHelper: ServerHelper,
    private val txnResponseMapper: PostGeneralResponseMapper,
    private val txnRequestMapper: PostUserRequestMapper
) {

    suspend fun request(req: PostUserRequest): Flow<DataState<PostGeneralResponse>> = flow {

        try {
            Log.d("NM", "PostValidateUser REQUEST")
            val res = apiHelper.postValidateUserRequest(txnRequestMapper.mapToEntity(req))
            Log.d("NM", "PostValidateUser Response  -> message=${res.message}")
            Log.d("NM", "PostValidateUser Response  -> responseCode=${res.responseCode}")

            emit(DataState.Success(txnResponseMapper.mapFromEntity(res)))
        } catch (e: Exception) {
            Log.d("NM", "PostValidateUser -> FAILURE ${e}")
            emit(DataState.Failure(e))
        }
    }

}