package com.nicomahnic.enerfiv2.apis.apiServer.networkModels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DevicesByEmailResponseNetworkEntity (

        @SerializedName("enable")
        @Expose
        val enable: Boolean,
        @SerializedName("id")
        @Expose
        val id: Int,
        @SerializedName("mac")
        @Expose
        val mac: String,
        @SerializedName("name")
        @Expose
        val name: String
)