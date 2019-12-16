package com.sawadikap.sawadikap.domain.model.request

import com.google.gson.annotations.SerializedName

data class ClothRequest(
    @SerializedName("id_user")
    val id: Int,
    @SerializedName("foto")
    val picture: String,
    @SerializedName("jenis_usia")
    val age: String,
    @SerializedName("jenis_ukuran")
    val size: String,
    @SerializedName("jenis_gender")
    val gender: String,
    @SerializedName("jenis_baju")
    val name: String,
    @SerializedName("status")
    val status: String
)