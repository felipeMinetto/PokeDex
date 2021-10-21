package com.fsm.pokedex.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pokemon(
    val id: Int,
    val name: String,
    @Json(name = "is_default") val isDefault: Boolean,
    val order: Int,
    val sprites: PokemonSprites,
    val species: PokemonSpecies,
    val stats: PokemonStat,
    val types: PokemonType
)

data class PokemonSprites(
    @Json(name = "front_default") val frontDefault: String,
    @Json(name = "front_shiny") val frontShiny: String,
    @Json(name = "front_female") val frontFemale: String,
    @Json(name = "front_shiny_female") val frontShinyFemale: String,
    @Json(name = "back_default") val backDefault: String,
    @Json(name = "back_shiny") val backShiny: String,
    @Json(name = "back_female") val backFemale: String,
    @Json(name = "back_shiny_female") val backShinyFemale: String
)

data class PokemonType(
    val slot: Int,
    val type: Type
)

data class PokemonStat(
    val stat: Stat,
    val effort: Int,
    val base_stat: Int
)