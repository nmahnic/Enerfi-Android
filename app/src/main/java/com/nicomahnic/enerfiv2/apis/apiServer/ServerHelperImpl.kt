package com.nicomahnic.enerfiv2.apis.apiServer

import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.*
import javax.inject.Inject

class ServerHelperImpl @Inject constructor(
    private val apiService: ServerService
): ServerHelper {

    override suspend fun postDeviceRequest(req: PostNewDeviceRequestNetworkEntity) : GeneralResponseNetworkEntity {
        return apiService.postNewDeviceRequest(req)
    }

    override suspend fun postNewUserRequest(req: PostNewUserRequestNetworkEntity) : GeneralResponseNetworkEntity {
        return apiService.postNewUserRequest(req)
    }

    override suspend fun postValidateUserRequest(req: PostUserRequestNetworkEntity) : GeneralResponseNetworkEntity {
        return apiService.postValidateUserRequest(req)
    }

    override suspend fun postDevicesByEmailRequest(req: PostUserRequestNetworkEntity) : List<DevicesByEmailResponseNetworkEntity>{
        return apiService.postDevicesByEmailRequest(req)
    }

    override suspend fun postFetchRemoteMeasures(req: PostUserAndDumRequestNetworkEntity) : List<MeasureByEmailAndDumResponseNetworkEntity>{
        return apiService.postFetchRemoteMeasures(req)
    }

}