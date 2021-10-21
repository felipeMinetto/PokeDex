package com.fsm.pokedex.data.model

data class PokemonSpecies(
    val id: Int,
    val name: String,
    val order: Int,
    val names: List<Name>
)
