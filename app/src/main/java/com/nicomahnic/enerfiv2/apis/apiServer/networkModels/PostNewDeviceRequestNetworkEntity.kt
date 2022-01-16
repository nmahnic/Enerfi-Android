package com.nicomahnic.enerfiv2.apis.apiServer.networkModels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostNewDeviceRequestNetworkEntity (

        @SerializedName("mac")
        @Expose
        val macAddress: String,
        @SerializedName("name")
        @Expose
        val deviceName: String,
        @SerializedName("email")
        @Expose
        val mail: String,
        @SerializedName("passwd")
        @Expose
        val passwd: String

)