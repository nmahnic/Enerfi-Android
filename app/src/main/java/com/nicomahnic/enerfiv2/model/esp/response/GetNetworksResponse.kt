package com.nicomahnic.enerfiv2.model.esp.response

import com.nicomahnic.enerfiv2.model.server.response.TransactionResponse

data class GetNetworksResponse(
    override val responseCode: Int,
    override val macAddress: String,
    val networks : List<String>?,
    val quality : List<String>?
) : TransactionResponse()