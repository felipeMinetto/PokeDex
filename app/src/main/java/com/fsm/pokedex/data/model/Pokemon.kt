package com.fsm.pokedex.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pokemon(
    val id: Int,
    val name: String,
    @Json(name = "is_default")
    val isDefault: Boolean,
    val order: Int,
    @Transient
    val sprites: PokemonSprites = PokemonSprites(id),
    @Json(name = "pokemon_v2_pokemonstats")
    val stats: List<PokemonStat>,
    @Json(name = "pokemon_v2_pokemontypes")
    val types: List<PokemonType>,
    @Transient
    var desc: String = ""
)

//Since the sprites are not retrievable via GraphQL I just hardcoded them.
data class PokemonSprites(
    private val pokemonId: Int,
    val officialArtwork: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$pokemonId.png",
    val front: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pokemonId.png",
    val back: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/$pokemonId.png",
    val frontShiny: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/$pokemonId.png",
    val backShiny: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/$pokemonId.png",
)

@JsonClass(generateAdapter = true)
data class PokemonType(
    val slot: Int,
    @Json(name = "pokemon_v2_type")
    val type: Type
)

@JsonClass(generateAdapter = true)
data class PokemonStat(
    @Json(name = "pokemon_v2_stat")
    val stat: Stat,
    val effort: Int,
    val base_stat: Int
)