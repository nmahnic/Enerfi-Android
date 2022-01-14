package com.nicomahnic.enerfiv2.apis.apiESP

import com.nicomahnic.enerfiv2.apis.apiESP.networkModels.GetNetworksResponseNetworkEntity
import com.nicomahnic.enerfiv2.apis.apiESP.networkModels.SetCredentialsResponseNetworkEntity
import retrofit2.http.*

interface ApiService {

    @GET("/wifi")
    suspend fun getNetworksReq(): GetNetworksResponseNetworkEntity

    @GET("/wifisave?")
    suspend fun setCredentialsReq(
            @Query("s") ssid : String,
            @Query("p") passwd: String,
    ): SetCredentialsResponseNetworkEntity

}