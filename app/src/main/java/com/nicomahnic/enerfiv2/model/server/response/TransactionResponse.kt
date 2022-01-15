package com.nicomahnic.enerfiv2.model.server.response

abstract class TransactionResponse {
    abstract val responseCode: Int
    abstract val macAddress: String
}

