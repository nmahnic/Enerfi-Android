package com.nicomahnic.enerfiv2.apis.apiServer.networkModels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostGeneralResponseNetworkEntity(

        @SerializedName("message")
        @Expose
        val message: String,
        @SerializedName("responseCode")
        @Expose
        val responseCode: Int
)