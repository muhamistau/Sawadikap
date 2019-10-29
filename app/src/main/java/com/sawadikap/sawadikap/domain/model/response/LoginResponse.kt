package com.sawadikap.sawadikap.domain.model.response

data class LoginResponse(
    val status: String,
    val id: Int,
    val username: String,
    val email: String
)