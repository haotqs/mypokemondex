package com.monastery.pokemondex.di

import android.app.Application
import com.monastery.pokemondex.MyApplication
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {

    fun activityComponentBuilder(): ActivityComponent.Builder
    fun fragmentComponentBuilder(): FragmentComponent.Builder

    fun inject(application: MyApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
