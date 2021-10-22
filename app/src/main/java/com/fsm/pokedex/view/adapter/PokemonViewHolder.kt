package com.fsm.pokedex.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fsm.pokedex.R
import com.fsm.pokedex.data.model.Pokemon
import com.fsm.pokedex.databinding.PokemonItemBinding

class PokemonViewHolder(private val binding: PokemonItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(pokemon: Pokemon) {
        binding.pokemon = pokemon
    }

    companion object {
        fun create(parent: ViewGroup): PokemonViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = DataBindingUtil.inflate<PokemonItemBinding>(layoutInflater, R.layout.pokemon_item, parent, false)
            return PokemonViewHolder(view)
        }
    }
}
