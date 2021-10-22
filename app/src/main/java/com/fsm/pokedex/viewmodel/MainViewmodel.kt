package com.fsm.pokedex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.fsm.pokedex.data.model.Pokemon
import com.fsm.pokedex.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainViewmodel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    fun collectPokemons(): Flow<PagingData<Pokemon>> {
        return repository.getPokemonFlow()
            .map { pagingData ->
                pagingData.map { pokemon ->
                    pokemon.apply {
                        desc = pokemon.types?.joinToString(" / ") { it.type.name } ?: ""
                    }
                }
            }.cachedIn(viewModelScope)
    }
}