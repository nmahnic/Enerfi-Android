package com.nicomahnic.enerfiv2.apis.apiServer.networkModels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostUserRequestNetworkEntity (

        @SerializedName("email")
        @Expose
        val mail: String,
        @SerializedName("password")
        @Expose
        val passwd: String
)