package com.nicomahnic.enerfiv2.apis.apiServer

import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.PostNewDeviceRequestNetworkEntity
import com.nicomahnic.enerfiv2.apis.apiServer.networkModels.PostNewDeviceResponseNetworkEntity

interface ServerHelper {

    suspend fun postRequest(req: PostNewDeviceRequestNetworkEntity) : PostNewDeviceResponseNetworkEntity

}