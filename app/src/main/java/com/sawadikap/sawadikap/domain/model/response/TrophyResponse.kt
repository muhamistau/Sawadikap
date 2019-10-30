package com.sawadikap.sawadikap.domain.model.response

import com.google.gson.annotations.SerializedName

data class TrophyResponse(
    @SerializedName("id_reward_sku")
    val id: Int,
    @SerializedName("id_reward")
    val rewardId: Int,
    @SerializedName("jenis_reward")
    val description: String
)