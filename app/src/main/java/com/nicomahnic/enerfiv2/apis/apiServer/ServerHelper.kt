package com.nicomahnic.enerfiv2.apis.apiServer

import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.*

interface ServerHelper {

    suspend fun postDeviceRequest(req: PostNewDeviceRequestNetworkEntity) : PostNewDeviceResponseNetworkEntity

    suspend fun postNewUserRequest(req: PostNewUserRequestNetworkEntity) : PostGeneralResponseNetworkEntity

    suspend fun postValidateUserRequest(req: PostUserRequestNetworkEntity) : PostGeneralResponseNetworkEntity

}