package com.nicomahnic.enerfiv2.apis.apiServer

import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.*

interface ServerHelper {

    suspend fun postDeviceRequest(req: PostNewDeviceRequestNetworkEntity) : GeneralResponseNetworkEntity

    suspend fun postNewUserRequest(req: PostNewUserRequestNetworkEntity) : GeneralResponseNetworkEntity

    suspend fun postValidateUserRequest(req: PostUserRequestNetworkEntity) : GeneralResponseNetworkEntity

    suspend fun postDevicesByEmailRequest(req: PostUserRequestNetworkEntity) : List<DevicesByEmailResponseNetworkEntity>

    suspend fun postFetchRemoteMeasures(req: PostUserAndDumRequestNetworkEntity) : List<MeasureByEmailAndDumResponseNetworkEntity>

    suspend fun postDeleteDeviceByEmailRequest(req: PostUserAndDumRequestNetworkEntity) : GeneralResponseNetworkEntity

}