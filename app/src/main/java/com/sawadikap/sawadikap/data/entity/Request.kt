package com.sawadikap.sawadikap.data.entity

import com.google.gson.annotations.SerializedName

data class Request(
    @SerializedName("id_request")
    val id: Int,
    @SerializedName("id_pakaian")
    val clothId: Int,
    @SerializedName("jenis_ukuran")
    val clothSize: String,
    @SerializedName("jenis_gender")
    val clothGender: String,
    @SerializedName("jenis_usia")
    val clothAge: String,
    @SerializedName("jenis_baju")
    val clothCategory: String,
    @SerializedName("foto")
    val clothPicture: String,
    val status: String,
    @SerializedName("created_at")
    val time: String
)