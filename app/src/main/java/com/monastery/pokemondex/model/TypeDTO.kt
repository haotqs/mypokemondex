package com.monastery.pokemondex.model

import com.google.gson.annotations.SerializedName

data class TypeDTO(
    @SerializedName("slot")
    var slot: Int? = null,
    @SerializedName("type")
    var type: TypeDetailDTO? = null
)

data class TypeDetailDTO(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("url")
    var url: String? = null
)