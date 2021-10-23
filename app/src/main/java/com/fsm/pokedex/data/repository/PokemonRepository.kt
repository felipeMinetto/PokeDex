package com.fsm.pokedex.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.fsm.pokedex.api.PokeDexApi
import com.fsm.pokedex.api.PokeDexGraphqlApi
import com.fsm.pokedex.data.model.Pokemon
import com.fsm.pokedex.data.repository.source.PokemonGQLPagingSource
import com.fsm.pokedex.data.repository.source.PokemonPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val api: PokeDexApi,
    private val apiGQL: PokeDexGraphqlApi
) {
    fun getPokemonFlow(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PokemonPagingSource(api) }
        ).flow
    }
    fun getPokemonGQLFlow(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PokemonGQLPagingSource(apiGQL) }
        ).flow
    }

    fun getPokemonLiveData(): LiveData<PagingData<Pokemon>> {
        return Pager(
            PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PokemonPagingSource(api) }
        ).liveData
    }

    companion object {
        const val PAGE_SIZE = 10
    }
}