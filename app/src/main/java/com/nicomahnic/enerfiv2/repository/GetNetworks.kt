package com.nicomahnic.enerfiv2.repository

import android.util.Log
import com.nicomahnic.enerfiv2.apis.apiESP.ApiHelper
import com.nicomahnic.enerfiv2.apis.apiESP.GetNetworksResponseMapper
import com.nicomahnic.enerfiv2.model.response.GetNetworksResponse
import com.nicomahnic.enerfiv2.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetNetworks @Inject constructor(
    private val apiHelper: ApiHelper,
    private val txnResponseMapper: GetNetworksResponseMapper,
) {

    suspend fun request(): Flow<DataState<GetNetworksResponse>> = flow {

        try{
            Log.d("NM", "GET NETWORKS REQUEST")
            val res = apiHelper.getNetworksReq()
            Log.d("NM", "Status Response  -> networks=${res.networks}")
            Log.d("NM", "Status Response  -> quality=${res.quality}")

            emit(DataState.Success(txnResponseMapper.mapFromEntity(res)))
        } catch (e: Exception){
            Log.d("NM","GET NETWORKS REQUEST -> FAILURE ${e}")
            emit(DataState.Failure(e))
        }
    }

}