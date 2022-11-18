package com.monastery.pokemondex.usecase

import com.monastery.pokemondex.api.ApiStoresInterface
import com.monastery.pokemondex.base.BaseResponse
import com.monastery.pokemondex.model.response.PokedexResponse
import com.monastery.pokemondex.model.response.PokemonFormResponse
import io.reactivex.Observable
import javax.inject.Inject

class PokemonUseCase @Inject constructor(val apiStoresInterface: ApiStoresInterface){

    fun getListPokemon(offset: String, limit: String): Observable<PokedexResponse> {
        return apiStoresInterface.onGetPokemonList(limit, offset)
    }

    fun getPokemonDetail(name: String): Observable<PokemonFormResponse> {
        return apiStoresInterface.getPokemonForm(name)
    }
}