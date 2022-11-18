package com.monastery.pokemondex.model

import com.google.gson.annotations.SerializedName

data class PokemonFormDTO(
    @SerializedName("form_name")
    var formName: String? = null,
    @SerializedName("form_names")
    var formNames: ArrayList<String> = arrayListOf(),
    @SerializedName("form_order")
    var formOrder: Int? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("is_battle_only")
    var isBattleOnly: Boolean = false,
    @SerializedName("is_default")
    var isDefault: Boolean = false,
    @SerializedName("is_mega")
    var isMega: Boolean = false,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("order")
    var order: Int? = null,
    @SerializedName("pokemon")
    var pokemon: PokeDexResultDTO? = null,
    @SerializedName("sprites")
    var sprites: SpritesDTO? = null,
    @SerializedName("types")
    var types: ArrayList<TypeDTO> = arrayListOf(),
    @SerializedName("version_group")
    var versionGroup: VersionGroupDTO? = null
)