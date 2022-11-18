package com.monastery.pokemondex.di

import android.app.Activity
import com.monastery.pokemondex.di.scope.UiScope
import dagger.Module
import dagger.Provides

/**
 * @author ex-cellpromote-ohta
 */
@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @UiScope
    fun provideActivity() = activity

    /*@Provides
    @UiScope
    fun provideEventTracker(activity: Activity): EventTracker = EventTracker(firebaseAnalytics, activity)*/
}
