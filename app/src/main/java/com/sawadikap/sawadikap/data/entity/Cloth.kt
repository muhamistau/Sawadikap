package com.sawadikap.sawadikap.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cloth(
    @SerializedName("id_pakaian")
    var id: Int? = null,
    @SerializedName("id_user")
    var idUser: Int? = null,
    @SerializedName("jenis_ukuran")
    var size: String? = null,
    @SerializedName("jenis_gender")
    var gender: String? = null,
    @SerializedName("jenis_usia")
    var age: String? = null,
    @SerializedName("jenis_baju")
    var type: String? = null,
    @SerializedName("foto")
    var photo: String? = null,
    @SerializedName("status")
    var status: String? = null
) : Parcelable