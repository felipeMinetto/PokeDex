package com.fsm.pokedex.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.fsm.pokedex.api.PokeDexApi
import com.fsm.pokedex.api.PokeDexGraphqlApi
import com.fsm.pokedex.data.model.Pokemon
import com.fsm.pokedex.data.model.UiModel
import com.fsm.pokedex.data.repository.source.PokemonGQLPagingSource
import com.fsm.pokedex.data.repository.source.PokemonPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    fun getPokemonGQLFlow(): Flow<PagingData<UiModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PokemonGQLPagingSource(apiGQL) }
        ).flow
            .map { pagingData ->
                pagingData.map {
                    it.apply {
                        desc = it.types.joinToString(" / ") { it.type.name }
                    }
                    UiModel.PokemonItem(it)
                }
            }
            .map {
                it.insertSeparators { before: UiModel.PokemonItem?, after: UiModel.PokemonItem? ->
                    when {
                        after == null ->
                            return@insertSeparators null
                        before == null ->
                            return@insertSeparators UiModel.SeparatorItem(after.generationName)
                        before.generationName != after.generationName ->
                            return@insertSeparators UiModel.SeparatorItem(after.generationName)
                        else -> null
                    }
                }
            }
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

    private val UiModel.PokemonItem.generationName: String
        get() = this.pokemon.specy.generation.generationNames.first().name

    companion object {
        const val PAGE_SIZE = 10
    }
}