package com.nicomahnic.enerfiv2.repository.server

import android.util.Log
import com.nicomahnic.enerfiv2.apis.apiServer.MeasureByEmailAndDumResponseMapper
import com.nicomahnic.enerfiv2.apis.apiServer.ServerHelper
import com.nicomahnic.enerfiv2.apis.apiServer.PostUserAndDumRequestMapper
import com.nicomahnic.enerfiv2.model.server.request.PostUserAndDumRequest
import com.nicomahnic.enerfiv2.model.server.response.MeasureByEmailAndDumResponse
import com.nicomahnic.enerfiv2.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class PostFetchRemoteMeasures @Inject constructor(
    private val apiHelper: ServerHelper,
    private val txnResponseMapper: MeasureByEmailAndDumResponseMapper,
    private val txnRequestMapper: PostUserAndDumRequestMapper
) {

    suspend fun request(req: PostUserAndDumRequest): Flow<DataState<List<MeasureByEmailAndDumResponse>>> = flow {

        try {
            Log.d("NM", "PostFetchRemoteMeasures REQUEST -> $req")
            val reu = txnRequestMapper.mapToEntity(req)
            val res = apiHelper.postFetchRemoteMeasures(reu)
            Log.d("NM", "PostFetchRemoteMeasures Response")

            emit(DataState.Success(txnResponseMapper.mapFromEntityList(res)))
        } catch (e: Exception) {
            Log.d("NM", "PostFetchRemoteMeasures -> FAILURE ${e}")
            emit(DataState.Failure(e))
        }
    }

}