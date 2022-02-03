package com.nicomahnic.enerfiv2.apis.apiServer

import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.*
import retrofit2.http.*

interface ServerService {

    @Headers("Content-Type: application/json")
    @POST("/dum/")
    suspend fun postNewDeviceRequest(@Body req: PostNewDeviceRequestNetworkEntity): GeneralResponseNetworkEntity

    @Headers("Content-Type: application/json")
    @POST("/user/")
    suspend fun postNewUserRequest(@Body req: PostNewUserRequestNetworkEntity): GeneralResponseNetworkEntity

    @Headers("Content-Type: application/json")
    @POST("/validateuser/")
    suspend fun postValidateUserRequest(@Body req: PostUserRequestNetworkEntity): GeneralResponseNetworkEntity

    @Headers("Content-Type: application/json")
    @POST("/listfulldumbyuser/")
    suspend fun postDevicesByEmailRequest(@Body req: PostUserRequestNetworkEntity) : List<DevicesByEmailResponseNetworkEntity>

    @Headers("Content-Type: application/json")
    @POST("/listmeasurebyuser/")
    suspend fun postFetchRemoteMeasures(@Body req: PostUserAndDumRequestNetworkEntity) : List<MeasureByEmailAndDumResponseNetworkEntity>

    @Headers("Content-Type: application/json")
    @POST("/deleteitem/")
    suspend fun postDeleteDeviceByEmailRequest(@Body req: PostUserAndDumRequestNetworkEntity) : GeneralResponseNetworkEntity
}