package com.nicomahnic.enerfiv2.apis.apiServer.networkModels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostNewUserRequestNetworkEntity (

        @SerializedName("name")
        @Expose
        val name: String,
        @SerializedName("email")
        @Expose
        val mail: String,
        @SerializedName("password")
        @Expose
        val passwd: String,
        @SerializedName("lastname")
        @Expose
        val lastname: String
)