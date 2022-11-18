package com.monastery.pokemondex.ext

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.monastery.pokemondex.MyApplication
import com.monastery.pokemondex.di.*


private fun Fragment.parentComponent(): AppComponent? = (requireContext().applicationContext as? MyApplication)?.component

@Suppress("UNCHECKED_CAST")
fun <C : FragmentComponent> Fragment.bindComponent(): Lazy<C> = lazy {
    parentComponent()
        ?.fragmentComponentBuilder()
        ?.fragmentModule(FragmentModule(this))
        ?.build() as C? ?: throw NullPointerException("Component Not Found.")
}

inline fun <reified T : ViewModel> Fragment.getViewModel(viewModelFactory: ViewModelFactory): T {
    return ViewModelProvider(this, viewModelFactory).get(T::class.java)
}
