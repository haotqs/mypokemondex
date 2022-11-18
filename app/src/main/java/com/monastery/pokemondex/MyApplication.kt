package com.monastery.pokemondex

import android.app.Application
import com.monastery.pokemondex.di.AppComponent
import com.monastery.pokemondex.di.DaggerAppComponent

class MyApplication : Application() {
    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().application(this).build()
    }
}
