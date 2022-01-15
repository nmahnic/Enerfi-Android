package com.nicomahnic.enerfiv2.apis.apiServer

import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.PostNewDeviceRequestNetworkEntity
import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.PostNewDeviceResponseNetworkEntity
import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.PostNewUserRequestNetworkEntity
import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.PostNewUserResponseNetworkEntity

interface ServerHelper {

    suspend fun postDeviceRequest(req: PostNewDeviceRequestNetworkEntity) : PostNewDeviceResponseNetworkEntity

    suspend fun postUserRequest(req: PostNewUserRequestNetworkEntity) : PostNewUserResponseNetworkEntity

}