package com.fsm.pokedex.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fsm.pokedex.data.model.UiModel
import com.fsm.pokedex.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewmodel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    fun collectPokemons(): Flow<PagingData<UiModel>> {
        return repository.getPokemonGQLFlow().cachedIn(viewModelScope)
    }
}