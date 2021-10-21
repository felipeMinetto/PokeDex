package com.fsm.pokedex.api

import com.fsm.pokedex.data.model.Pokemon
import retrofit2.http.GET

interface PokeDexApi {
    @GET("pokemon")
    fun getAllPokemons(): List<Pokemon>

    @GET("pokemon/{id}")
    fun getPokemon(id: Int): List<Pokemon>

    @GET("pokemon/{name}")
    fun getPokemon(name: String): List<Pokemon>
}