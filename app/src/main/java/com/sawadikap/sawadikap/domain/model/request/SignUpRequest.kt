package com.sawadikap.sawadikap.domain.model.request

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    val email: String,
    @SerializedName("fullname")
    val fullname: String,
    val phone: String,
    val password: String,
    val username: String
)