package com.monastery.pokemondex.helper

import com.monastery.pokemondex.MainActivity
import com.monastery.pokemondex.ui.PokemonFragment
import javax.inject.Inject

class FragmentAggregator @Inject constructor(private val mMainActivity: MainActivity) {


    fun openPokemonList() {
        mMainActivity?.replaceFragment(PokemonFragment())
    }
}