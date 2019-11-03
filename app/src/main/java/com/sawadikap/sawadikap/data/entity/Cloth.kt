package com.sawadikap.sawadikap.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cloth(
    @SerializedName("id_pakaian")
    val id: Int,
    @SerializedName("id_user")
    val idUser: Int,
    @SerializedName("jenis_ukuran")
    val size: String,
    @SerializedName("jenis_gender")
    val gender: String,
    @SerializedName("jenis_usia")
    val age: String,
    @SerializedName("jenis_baju")
    val type: String,
    @SerializedName("foto")
    val photo: String,
    @SerializedName("status")
    val status: String
) : Parcelable