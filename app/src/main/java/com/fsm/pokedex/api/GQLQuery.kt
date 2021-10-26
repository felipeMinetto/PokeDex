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
    val query: String = "query pokemon(\$limit: Int, \$offset: Int) {" +
            "  pokemon_v2_pokemon(limit: \$limit, offset: \$offset, order_by: {pokemon_v2_pokemonspecy: {generation_id: asc}, id: asc}) {" +
            "    id" +
            "    name" +
            "    is_default" +
            "    order" +
            "    pokemon_v2_pokemonstats {" +
            "      base_stat" +
            "      effort" +
            "      pokemon_v2_stat {" +
            "        id" +
            "        name" +
            "      }" +
            "    }" +
            "    pokemon_v2_pokemontypes {" +
            "      slot" +
            "      pokemon_v2_type {" +
            "        id" +
            "        name" +
            "      }" +
            "    }" +
            "    pokemon_v2_pokemonspecy {" +
            "      pokemon_v2_generation {" +
            "        name" +
            "        id" +
            "        pokemon_v2_generationnames(where: {pokemon_v2_language: {iso3166: {_eq: \"us\"}}}) {" +
            "          name" +
            "          language_id" +
            "        }" +
            "      }" +
            "      generation_id" +
            "    }" +
            "  }" +
            "}"
)