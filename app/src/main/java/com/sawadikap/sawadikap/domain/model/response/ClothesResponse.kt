package com.sawadikap.sawadikap.domain.model.response

import com.sawadikap.sawadikap.data.entity.Cloth

data class ClothesResponse(
    var clothes: List<Cloth>
)