package com.nicomahnic.enerfiv2.apis.apiServer

import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.PostNewDeviceRequestNetworkEntity
import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.PostNewDeviceResponseNetworkEntity
import retrofit2.http.*

interface ServerService {

    @Headers("Content-Type: application/json")
    @POST("/User")
    suspend fun postRequest(@Body req: PostNewDeviceRequestNetworkEntity): PostNewDeviceResponseNetworkEntity

}