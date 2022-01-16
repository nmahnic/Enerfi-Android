package com.nicomahnic.enerfiv2.apis.apiServer

import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.*
import javax.inject.Inject

class ServerHelperImpl @Inject constructor(
    private val apiService: ServerService
): ServerHelper {

    override suspend fun postDeviceRequest(req: PostNewDeviceRequestNetworkEntity) : PostGeneralResponseNetworkEntity {
        return apiService.postNewDeviceRequest(req)
    }

    override suspend fun postNewUserRequest(req: PostNewUserRequestNetworkEntity) : PostGeneralResponseNetworkEntity {
        return apiService.postNewUserRequest(req)
    }

    override suspend fun postValidateUserRequest(req: PostUserRequestNetworkEntity) : PostGeneralResponseNetworkEntity {
        return apiService.postValidateUserRequest(req)
    }

}