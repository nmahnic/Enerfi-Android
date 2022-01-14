package com.nicomahnic.enerfiv2.apis.apiESP

import com.nicomahnic.enerfiv2.apis.apiESP.networkModels.GetNetworksResponseNetworkEntity
import com.nicomahnic.enerfiv2.apis.apiESP.networkModels.SetCredentialsResponseNetworkEntity

interface ApiHelper {

    suspend fun getNetworksReq() : GetNetworksResponseNetworkEntity

    suspend fun setCredentialsReq(ssid: String, passwd: String): SetCredentialsResponseNetworkEntity

}