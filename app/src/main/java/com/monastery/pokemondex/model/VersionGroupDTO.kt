package com.monastery.pokemondex.model

import com.google.gson.annotations.SerializedName

data class VersionGroupDTO(

    @SerializedName("name")
    var name: String? = null,
    @SerializedName("url")
    var url : String? = null
)