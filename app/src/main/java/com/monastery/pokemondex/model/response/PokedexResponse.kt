package com.monastery.pokemondex.model.response

import com.google.gson.annotations.SerializedName
import com.monastery.pokemondex.base.BaseResponse
import com.monastery.pokemondex.model.PokeDexResultDTO

class PokedexResponse(
    @SerializedName("count")
    var count: Int? = null,
    @SerializedName("next")
    var next: String? = null,
    @SerializedName("previous")
    var previous: String? = null,
    @SerializedName("results")
    var result: ArrayList<PokeDexResultDTO> = arrayListOf()
): BaseResponse()