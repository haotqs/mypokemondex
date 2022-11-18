package com.monastery.pokemondex.modelview

import com.monastery.pokemondex.api.ApiConsumer
import com.monastery.pokemondex.api.SingleLiveEvent
import com.monastery.pokemondex.base.BaseModelView
import com.monastery.pokemondex.base.BaseResponse
import com.monastery.pokemondex.ext.logd
import com.monastery.pokemondex.model.PokeDexResultDTO
import com.monastery.pokemondex.model.PokemonFormDTO
import com.monastery.pokemondex.model.response.PokedexResponse
import com.monastery.pokemondex.model.response.PokemonFormResponse
import com.monastery.pokemondex.usecase.PokemonUseCase
import javax.inject.Inject

class PokemonModelView @Inject constructor(val useCase: PokemonUseCase): BaseModelView() {

    val onGetPokemonListSuccess: SingleLiveEvent<ArrayList<PokemonFormResponse?>> = SingleLiveEvent()
    private var arrPokemonForm: ArrayList<PokemonFormResponse?> = arrayListOf()
    private var setName : Set<String> = mutableSetOf()
    private var onFinish = false

    private var offset = 0
    private val limit = 30
    fun getPokemonList(isRefresh: Boolean = false) {
        if (isRefresh) {
            onFinish = false
            offset = 0
            arrPokemonForm.clear()
        }
        addSubscription(
            useCase.getListPokemon(offset.toString(), limit.toString()),
            object: ApiConsumer<PokedexResponse> {
                override fun onSuccess(response: PokedexResponse) {
                    offset += limit
                    response.result.let {
                        for (i in 0 until it.size) {
                            onGetPokemonForm(it[i].name ?: "")
                            "on Load Name 1 ${it[i].name}".logd()

                            if (i == it.size -1) {
                                onFinish = true
                                "Check finish ".logd()
                            }
                        }
                    }
                }

                override fun onFailure(throwable: Throwable) { }

                override fun onLoading(isShowLoading: Boolean) { }
            })
    }

    private fun onGetPokemonForm(name: String) {
        addSubscription(
            useCase.getPokemonDetail(name),
            object: ApiConsumer<PokemonFormResponse> {
                override fun onSuccess(response: PokemonFormResponse) {
                    "on Load Name 2 ${response.name}".logd()
                    if (response.name == name)
                        arrPokemonForm.add(response)

                    if (onFinish) {
                        onGetPokemonListSuccess.postValue(arrPokemonForm)
                    }
                }

                override fun onFailure(throwable: Throwable) {
                }

                override fun onLoading(isShowLoading: Boolean) {
                }

            }
        )
    }

}