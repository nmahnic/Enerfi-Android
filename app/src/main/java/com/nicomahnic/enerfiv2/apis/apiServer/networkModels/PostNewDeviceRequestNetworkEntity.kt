package com.nicomahnic.enerfiv2.apis.apiServer.networkModels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostNewDeviceRequestNetworkEntity (

        @SerializedName("macAddress")
        @Expose
        val macAddress: String

)