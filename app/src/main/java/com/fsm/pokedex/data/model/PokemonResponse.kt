package com.fsm.pokedex.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    var results: List<Pokemon>
)
