package com.fsm.pokedex.api

import com.fsm.pokedex.data.model.Pokemon
import com.fsm.pokedex.data.model.PokemonResponse
import retrofit2.http.GET

interface PokeDexApi {
    @GET("pokemon")
    fun getAllPokemons(): PokemonResponse

    @GET("pokemon/{id}")
    fun getPokemon(id: Int): Pokemon

    @GET("pokemon/{name}")
    fun getPokemon(name: String): Pokemon
}