package com.monastery.pokemondex.di

import com.monastery.pokemondex.di.scope.UiScope
import com.monastery.pokemondex.ui.HomeFragment
import com.monastery.pokemondex.ui.PokemonFragment
import dagger.Subcomponent
/**
 * @author ex-cellpromote-ohta
 */
@UiScope
@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(fragment: HomeFragment)
    fun inject(fragment: PokemonFragment)

    @Subcomponent.Builder
    interface Builder {
        fun fragmentModule(fragmentModule: FragmentModule): Builder
        fun build(): FragmentComponent
    }
}
