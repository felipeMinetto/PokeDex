package com.fsm.pokedex.data.repository.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fsm.pokedex.api.PokeDexApi
import com.fsm.pokedex.data.model.Pokemon
import com.fsm.pokedex.data.repository.PokemonRepository
import retrofit2.HttpException
import java.io.IOException

class PokemonPagingSource(
    private val api: PokeDexApi
) : PagingSource<Int, Pokemon>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            val page = params.key ?: 0
            val offset = params.loadSize * page
            val response = api.getAllPokemons(params.loadSize, offset).apply {
                results = results.map { pokemon ->
                    api.getPokemon(pokemon.name)
                }
            }
            val prevKey =
                if (page == 0) {
                    null
                } else {
                    page - 1
                }
            val nextKey =
                if (response.results.isEmpty()) {
                    null
                } else {
                    ((params.loadSize + offset) / PokemonRepository.PAGE_SIZE)
                }
            LoadResult.Page(
                response.results,
                prevKey,
                nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}
