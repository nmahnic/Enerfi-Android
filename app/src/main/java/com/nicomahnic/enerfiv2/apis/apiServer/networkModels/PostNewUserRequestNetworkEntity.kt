package com.nicomahnic.enerfiv2.apis.apiServer.networkModels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostNewUserRequestNetworkEntity (

        @SerializedName("name")
        @Expose
        val name: String,
        @SerializedName("mail")
        @Expose
        val mail: String,
        @SerializedName("passwd")
        @Expose
        val passwd: String
)