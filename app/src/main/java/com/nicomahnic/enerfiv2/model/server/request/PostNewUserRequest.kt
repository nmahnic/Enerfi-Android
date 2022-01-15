package com.nicomahnic.enerfiv2.model.server.request

data class PostNewUserRequest (
    val name: String,
    val mail: String,
    val passwd: String
)