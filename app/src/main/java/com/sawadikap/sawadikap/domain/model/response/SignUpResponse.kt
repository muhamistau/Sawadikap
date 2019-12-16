package com.sawadikap.sawadikap.domain.model.response

data class SignUpResponse(
    val email: String,
    val fullname: String,
    val phone: String,
    val id: Int
)