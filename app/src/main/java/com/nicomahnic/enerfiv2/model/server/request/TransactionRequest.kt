package com.nicomahnic.enerfiv2.model.server.request

abstract class TransactionRequest {
    abstract val androidId: String
    abstract val regId: String
}