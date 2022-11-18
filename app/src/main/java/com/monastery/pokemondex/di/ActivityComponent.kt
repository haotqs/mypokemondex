package com.monastery.pokemondex.di

import com.monastery.pokemondex.MainActivity
import com.monastery.pokemondex.di.scope.UiScope
import dagger.Subcomponent

@UiScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)

    @Subcomponent.Builder
    interface Builder {
        fun activityModule(activityModule: ActivityModule): Builder
        fun build(): ActivityComponent
    }
}
