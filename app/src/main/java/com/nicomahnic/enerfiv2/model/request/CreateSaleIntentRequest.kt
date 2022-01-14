package com.nicomahnic.enerfiv2.model.request

data class CreateSaleIntentRequest (
        override val androidId: String,
        override val regId: String,
        val amount: Double,
        val orderId: String
) : TransactionRequest()