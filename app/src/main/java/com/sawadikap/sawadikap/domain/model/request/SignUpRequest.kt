package com.sawadikap.sawadikap.domain.model.request

data class SignUpRequest(
    val email: String,
    val password: String,
    val fullName: String
)