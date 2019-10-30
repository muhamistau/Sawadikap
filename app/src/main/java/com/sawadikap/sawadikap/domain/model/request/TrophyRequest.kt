package com.sawadikap.sawadikap.domain.model.request

import com.google.gson.annotations.SerializedName

data class TrophyRequest(
    @SerializedName("id_user")
    val userId: Int,
    @SerializedName("id_reward")
    val trophyId: Int
)