package com.nicomahnic.enerfiv2.model.server.response

data class MeasureByEmailAndDumResponse(
    val vrms: Float,
    val irms: Float,
    val activePower: Float,
    val powerFactor: String,
    val thd_i: String,
    val thd_v: String,
    val cosPhi: String,
    val timeStamp: String
)
