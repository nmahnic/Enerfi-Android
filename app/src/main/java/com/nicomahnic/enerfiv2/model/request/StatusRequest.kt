package com.nicomahnic.enerfiv2.model.request

data class StatusRequest (
        override val androidId: String,
        override val regId: String,
        val extRefId: String
) : TransactionRequest()