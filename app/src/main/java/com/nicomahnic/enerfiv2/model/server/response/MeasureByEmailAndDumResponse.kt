package com.nicomahnic.enerfiv2.model.server.response

import java.sql.Timestamp

data class MeasureByEmailAndDumResponse(
    val vrms: Float,
    val irms: Float,
    val activePower: Float,
    val powerFactor: Float,
    val thd: Float,
    val cosPhi: Float,
    val timeStamp: String
)
