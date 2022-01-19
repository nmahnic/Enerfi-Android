package com.nicomahnic.enerfiv2.apis.apiServer.networkModels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostUserAndDumRequestNetworkEntity (
    @SerializedName("email")
    @Expose
    val mail: String,
    @SerializedName("passwd")
    @Expose
    val passwd: String,
    @SerializedName("mac")
    @Expose
    val mac: String
)