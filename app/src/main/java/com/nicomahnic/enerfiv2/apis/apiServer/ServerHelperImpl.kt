package com.nicomahnic.enerfiv2.apis.apiServer

import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.*
import javax.inject.Inject

class ServerHelperImpl @Inject constructor(
    private val apiService: ServerService
): ServerHelper {

    override suspend fun postDeviceRequest(req: PostNewDeviceRequestNetworkEntity) : PostNewDeviceResponseNetworkEntity = apiService.postDeviceRequest(req)

    override suspend fun postNewUserRequest(req: PostNewUserRequestNetworkEntity) : PostGeneralResponseNetworkEntity = apiService.postNewUserRequest(req)

    override suspend fun postValidateUserRequest(req: PostUserRequestNetworkEntity) : PostGeneralResponseNetworkEntity = apiService.postValidateUserRequest(req)

}