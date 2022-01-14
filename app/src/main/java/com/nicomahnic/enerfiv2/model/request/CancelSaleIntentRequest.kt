package com.nicomahnic.enerfiv2.model.request

data class CancelSaleIntentRequest (
        override val androidId: String,
        override val regId: String,
        val extRefId: String
) : TransactionRequest()