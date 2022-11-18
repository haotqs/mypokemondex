package com.monastery.pokemondex.api

import com.monastery.pokemondex.base.BaseResponse
import com.monastery.pokemondex.model.response.PokedexResponse
import com.monastery.pokemondex.model.response.PokemonFormResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface ApiStoresInterface {

    @GET("pokemon")
    fun onGetPokemonList(@Query("limit") limit: String
                         , @Query("offset") offset: String): Observable<PokedexResponse>



    @GET("pokemon-form/{path}")
    fun getPokemonForm(@Path("path") path: String): Observable<PokemonFormResponse>

}