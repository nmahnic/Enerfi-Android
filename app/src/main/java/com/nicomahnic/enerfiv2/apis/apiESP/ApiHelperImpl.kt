package com.nicomahnic.enerfiv2.apis.apiESP

import com.nicomahnic.enerfiv2.apis.apiESP.networkModels.GetNetworksResponseNetworkEntity
import com.nicomahnic.enerfiv2.apis.apiESP.networkModels.SetCredentialsResponseNetworkEntity
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
): ApiHelper {

    override suspend fun getNetworksReq() : GetNetworksResponseNetworkEntity = apiService.getNetworksReq()

    override suspend fun setCredentialsReq(ssid: String, passwd: String) : SetCredentialsResponseNetworkEntity = apiService.setCredentialsReq(ssid, passwd)

}