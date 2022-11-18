package com.monastery.pokemondex.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.monastery.pokemondex.MyApplication
import com.monastery.pokemondex.R
import com.monastery.pokemondex.base.BaseFragment
import com.monastery.pokemondex.di.DaggerAppComponent
import com.monastery.pokemondex.di.FragmentComponent
import com.monastery.pokemondex.ext.bindComponent
import com.monastery.pokemondex.helper.FragmentAggregator
import javax.inject.Inject

class HomeFragment: BaseFragment(R.layout.fragment_home) {

    private val compoment: FragmentComponent by bindComponent()
    @Inject
    lateinit var fragmentAggregator: FragmentAggregator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun init(view: View) {
        view.findViewById<Button>(R.id.buttonPokemonDex).setOnClickListener {
            mMainActivity?.replaceFragment(PokemonFragment())
        }
    }
}