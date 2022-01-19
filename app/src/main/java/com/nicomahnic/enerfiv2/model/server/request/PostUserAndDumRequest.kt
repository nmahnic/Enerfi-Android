package com.nicomahnic.enerfiv2.model.server.request

data class PostUserAndDumRequest (
    val mail: String,
    val passwd: String,
    val mac: String
)