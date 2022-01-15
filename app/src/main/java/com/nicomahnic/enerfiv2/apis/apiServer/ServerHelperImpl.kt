package com.nicomahnic.enerfiv2.apis.apiServer

import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.PostNewDeviceRequestNetworkEntity
import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.PostNewDeviceResponseNetworkEntity
import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.PostNewUserRequestNetworkEntity
import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.PostNewUserResponseNetworkEntity
import javax.inject.Inject

class ServerHelperImpl @Inject constructor(
    private val apiService: ServerService
): ServerHelper {

    override suspend fun postDeviceRequest(req: PostNewDeviceRequestNetworkEntity) : PostNewDeviceResponseNetworkEntity = apiService.postDeviceRequest(req)

    override suspend fun postUserRequest(req: PostNewUserRequestNetworkEntity) : PostNewUserResponseNetworkEntity = apiService.postUserRequest(req)

}