package com.nicomahnic.enerfiv2.model.response

data class GetNetworksResponse(
    override val responseCode: Int,
    override val macAddress: String,
    val networks : List<String>?,
    val quality : List<String>?
) : TransactionResponse()