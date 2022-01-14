package com.nicomahnic.enerfiv2.apis.apiESP.networkModels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.nicomahnic.enerfiv2.apis.TxnResponseNetworkEntity

data class GetNetworksResponseNetworkEntity(

    @SerializedName("responseCode")
    @Expose
    override val responseCode: Int,

    @SerializedName("macAddress")
    @Expose
    override val macAddress: String,

    @SerializedName("networks")
    @Expose
    val networks: List<String>?,

    @SerializedName("quality")
    @Expose
    val quality: List<String>?

) : TxnResponseNetworkEntity()