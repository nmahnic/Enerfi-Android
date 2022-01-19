package com.nicomahnic.enerfiv2.model.server.response

data class MeasureByEmailAndDumResponse(
    val vrms: Float,
    val irms: Float,
    val activePower: Float,
    val powerFactor: Float,
    val thd: Float,
    val cosPhi: Float,
    val timeStamp: String
)
