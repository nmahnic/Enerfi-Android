package com.nicomahnic.enerfiv2.apis.apiServer.networkModels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MeasureByEmailAndDumResponseNetworkEntity (

        @SerializedName("timestamp")
        @Expose
        val timeStamp: String,
        @SerializedName("vrms")
        @Expose
        val vrms: Float,
        @SerializedName("irms")
        @Expose
        val irms: Float,
        @SerializedName("active_power")
        @Expose
        val activePower: Float,
        @SerializedName("pf")
        @Expose
        val powerFactor: Float,
        @SerializedName("thd_i")
        @Expose
        val thd_i: Float,
        @SerializedName("thd_v")
        @Expose
        val thd_v: Float,
        @SerializedName("cos_phi")
        @Expose
        val cosPhi: Float,

        )