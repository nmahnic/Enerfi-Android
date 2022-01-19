package com.nicomahnic.enerfiv2.repository.server

import android.util.Log
import com.nicomahnic.enerfiv2.apis.apiServer.GeneralResponseMapper
import com.nicomahnic.enerfiv2.apis.apiServer.PostUserRequestMapper
import com.nicomahnic.enerfiv2.apis.apiServer.ServerHelper
import com.nicomahnic.enerfiv2.model.server.request.PostUserRequest
import com.nicomahnic.enerfiv2.model.server.response.GeneralResponse
import com.nicomahnic.enerfiv2.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class PostValidateUser @Inject constructor(
    private val apiHelper: ServerHelper,
    private val txnResponseMapper: GeneralResponseMapper,
    private val txnRequestMapper: PostUserRequestMapper
) {

    suspend fun request(req: PostUserRequest): Flow<DataState<GeneralResponse>> = flow {

        try {
            Log.d("NM", "PostValidateUser REQUEST -> $req")
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