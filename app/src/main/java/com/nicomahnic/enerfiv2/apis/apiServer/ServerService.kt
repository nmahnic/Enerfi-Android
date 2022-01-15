package com.nicomahnic.enerfiv2.apis.apiServer

import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.PostNewDeviceRequestNetworkEntity
import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.PostNewDeviceResponseNetworkEntity
import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.PostNewUserRequestNetworkEntity
import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.PostNewUserResponseNetworkEntity
import retrofit2.http.*

interface ServerService {

    @Headers("Content-Type: application/json")
    @POST("/Device")
    suspend fun postDeviceRequest(@Body req: PostNewDeviceRequestNetworkEntity): PostNewDeviceResponseNetworkEntity

    @Headers("Content-Type: application/json")
    @POST("/User")
    suspend fun postUserRequest(@Body req: PostNewUserRequestNetworkEntity): PostNewUserResponseNetworkEntity

}