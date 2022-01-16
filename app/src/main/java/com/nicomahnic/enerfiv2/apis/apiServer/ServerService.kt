package com.nicomahnic.enerfiv2.apis.apiServer

import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.*
import retrofit2.http.*

interface ServerService {

    @Headers("Content-Type: application/json")
    @POST("/dum/")
    suspend fun postNewDeviceRequest(@Body req: PostNewDeviceRequestNetworkEntity): PostGeneralResponseNetworkEntity

    @Headers("Content-Type: application/json")
    @POST("/user/")
    suspend fun postNewUserRequest(@Body req: PostNewUserRequestNetworkEntity): PostGeneralResponseNetworkEntity

    @Headers("Content-Type: application/json")
    @POST("/validateuser/")
    suspend fun postValidateUserRequest(@Body req: PostUserRequestNetworkEntity): PostGeneralResponseNetworkEntity

}