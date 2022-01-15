package com.nicomahnic.enerfiv2.repository.esp

import android.util.Log
import com.nicomahnic.enerfiv2.apis.apiESP.ApiHelper
import com.nicomahnic.enerfiv2.apis.apiESP.SetCredentialsResponseMapper
import com.nicomahnic.enerfiv2.model.esp.response.SetCredentialsResponse
import com.nicomahnic.enerfiv2.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class SetCredentials @Inject constructor(
    private val apiHelper: ApiHelper,
    private val txnResponseMapper: SetCredentialsResponseMapper,
) {

    suspend fun request(ssid: String, passwd: String): Flow<DataState<SetCredentialsResponse>> = flow {

        try{
            Log.d("NM", "SET CREDENTIALS REQUEST")
            val res = apiHelper.setCredentialsReq(ssid, passwd)
            Log.d("NM", "Status Response ${res.responseCode}")

            emit(DataState.Success(txnResponseMapper.mapFromEntity(res)))
        } catch (e: Exception){
            Log.d("NM","SET CREDENTIALS REQUEST -> FAILURE ${e}")
            emit(DataState.Failure(e))
        }
    }

}