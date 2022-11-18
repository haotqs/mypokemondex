package com.monastery.pokemondex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.monastery.pokemondex.R
import com.monastery.pokemondex.model.PokeDexResultDTO
import com.monastery.pokemondex.model.PokemonFormDTO
import com.monastery.pokemondex.model.response.PokemonFormResponse

class PokemonAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var listResult: ArrayList<PokemonFormResponse?> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return PokemonResultHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PokemonResultHolder).onBind(listResult[position])
    }

    override fun getItemCount(): Int = listResult.size

    inner class PokemonResultHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun onBind(data: PokemonFormResponse?) {
            itemView.findViewById<TextView>(R.id.textViewPokemonName).text = data?.name

            Glide.with(itemView.context)
                .load(data?.sprites?.frontDefault)
                .into(itemView.findViewById<ImageView>(R.id.imageViewPokemonAppearance))
        }
    }
}