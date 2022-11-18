package com.monastery.pokemondex.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.monastery.pokemondex.R
import com.monastery.pokemondex.adapter.PokemonAdapter
import com.monastery.pokemondex.base.BaseMVVMFragment
import com.monastery.pokemondex.di.FragmentComponent
import com.monastery.pokemondex.di.ViewModelFactory
import com.monastery.pokemondex.ext.bindComponent
import com.monastery.pokemondex.ext.getViewModel
import com.monastery.pokemondex.ext.logd
import com.monastery.pokemondex.model.PokeDexResultDTO
import com.monastery.pokemondex.model.PokemonFormDTO
import com.monastery.pokemondex.model.response.PokemonFormResponse
import com.monastery.pokemondex.modelview.PokemonModelView
import javax.inject.Inject

class PokemonFragment: BaseMVVMFragment<PokemonModelView>(R.layout.fragment_pokemon) {

    private val compoment: FragmentComponent by bindComponent()

    private var mAdapter: PokemonAdapter? = null
    private var mResult: ArrayList<PokemonFormResponse?> = arrayListOf()
    lateinit var mRecycler:RecyclerView
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        compoment.inject(this)
        viewModel = getViewModel(viewModelFactory)
        super.onCreate(savedInstanceState)
    }
    override fun init(view: View) {
        mRecycler = view.findViewById<RecyclerView>(R.id.rcvPokemonList)
        view.findViewById<TextView>(R.id.textViewToolbarTitle).text = "Pokédex"
        "onSetData ${mResult.size}".logd()
        mAdapter = PokemonAdapter().apply {
            this.listResult = mResult
        }

        mRecycler.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = mAdapter
            setHasFixedSize(false)
        }

        viewModel?.getPokemonList(true)
    }

    override fun setupObserveModelView(viewModel: PokemonModelView?) {
        viewModel?.onGetPokemonListSuccess?.observe(
            this,
            Observer {
                mResult.addAll(it)
                "on Check finish ${mResult.size} ".logd()
                mAdapter?.notifyDataSetChanged()
            }
        )
    }

}