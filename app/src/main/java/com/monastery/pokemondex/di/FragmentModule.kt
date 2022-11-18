package com.monastery.pokemondex.di

import android.app.Activity
import androidx.fragment.app.Fragment
import com.monastery.pokemondex.MainActivity
import com.monastery.pokemondex.base.BaseActivity
import com.monastery.pokemondex.di.scope.UiScope
import dagger.Module
import dagger.Provides
/**
 * @author ex-cellpromote-ohta
 */
@Module
class FragmentModule(private val fragment: Fragment) {

    @UiScope
    @Provides
    fun provideActivity(): Activity = fragment.activity ?: throw NullPointerException(
        """
            Activity is Null. Please do the injection after the attach() of the fragment is called
            """
    )
    @UiScope
    @Provides
    fun provideMainActivity(): MainActivity = fragment.activity as MainActivity

    @UiScope
    @Provides
    fun provideBaseActivity(): BaseActivity = fragment.activity as BaseActivity

}
