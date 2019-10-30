package com.sawadikap.sawadikap.data.entity

import com.google.gson.annotations.SerializedName

data class Trophy(
    @SerializedName("id_reward_sku")
    val id: Int,
    @SerializedName("syarat_jumlah")
    val requirement: Int,
    @SerializedName("jenis_reward")
    val trophyName: String
)