package com.nicomahnic.enerfiv2.apis.apiServer.networkModels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.nicomahnic.enerfiv2.apis.TxnResponseNetworkEntity

data class PostNewUserResponseNetworkEntity(

        @SerializedName("responseCode")
        @Expose
        override val responseCode: Int,

        @SerializedName("macAddress")
        @Expose
        override val macAddress: String
) : TxnResponseNetworkEntity()