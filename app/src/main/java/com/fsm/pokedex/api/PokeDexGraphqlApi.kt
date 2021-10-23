package com.fsm.pokedex.api

import com.fsm.pokedex.data.model.Pokemon
import retrofit2.http.Body
import retrofit2.http.POST

interface PokeDexGraphqlApi {

    @POST("v1beta")
    suspend fun getPokemons(@Body body: PokemonQuery): List<Pokemon>
}