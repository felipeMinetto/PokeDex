package com.fsm.pokedex.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonQuery(
    @Transient
    val limit: Int = 0,
    @Transient
    val offset: Int = 0,
    val operationName: String = "pokemon",
    val variables: Map<String, Int> = mapOf(
        "limit" to limit,
        "offset" to offset
    ),
    val query: String = "query pokemon(\$limit: Int, \$offset:Int) { pokemon_v2_pokemon(limit: \$limit, offset: \$offset) { id name is_default order pokemon_v2_pokemonsprites { sprites } pokemon_v2_pokemonstats { base_stat effort pokemon_v2_stat { id name } } pokemon_v2_pokemontypes { slot pokemon_v2_type { id name } } } }"
)