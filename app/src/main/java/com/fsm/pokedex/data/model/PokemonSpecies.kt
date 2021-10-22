package com.fsm.pokedex.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonSpecies(
    val id: Int,
    val name: String,
    val order: Int,
    val names: List<Name>
)
