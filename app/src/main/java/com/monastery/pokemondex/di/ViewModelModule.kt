package com.monastery.pokemondex.di

import androidx.lifecycle.ViewModel
import com.monastery.pokemondex.modelview.PokemonModelView
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * ViewModelを提供するModule
 *
 * ViewModelを新たに作ったときにはこちらに登録する
 * [ViewModelFactory]が使用する
 *
 * @see ViewModelKey
 * @see ViewModelFactory
 * @author ex-cellpromote-ohta
 */
@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PokemonModelView::class)
    abstract fun onBindPokemonModelView(viewModel: PokemonModelView): ViewModel

}
