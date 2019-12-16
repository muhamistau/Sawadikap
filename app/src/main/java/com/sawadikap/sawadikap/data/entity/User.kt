package com.sawadikap.sawadikap.data.entity

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id_user")
    val id: Int,
    @SerializedName("fullname")
    val name: String,
    val email: String,
    val phone: String,
    val password: String
)