package com.nicomahnic.enerfiv2.model.response

data class SetCredentialsResponse(
    override val responseCode: Int,
    override val macAddress: String
) : TransactionResponse()