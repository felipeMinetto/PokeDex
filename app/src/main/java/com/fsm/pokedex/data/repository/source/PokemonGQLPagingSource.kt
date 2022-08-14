package com.fsm.pokedex.data.repository.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fsm.pokedex.api.PokeDexGraphqlApi
import com.fsm.pokedex.api.PokemonQuery
import com.fsm.pokedex.data.model.Pokemon
import com.fsm.pokedex.data.repository.PokemonRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PokemonGQLPagingSource @Inject constructor(
    private val api: PokeDexGraphqlApi
) : PagingSource<Int, Pokemon>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            val page = params.key ?: 0
            val offset = params.loadSize * page
            val results = api.getPokemons(PokemonQuery(params.loadSize, offset))
            val prevKey =
                if (page == 0) {
                    null
                } else {
                    page - 1
                }
            val nextKey =
                if (results.isEmpty()) {
                    null
                } else {
                    ((params.loadSize + offset) / PokemonRepository.PAGE_SIZE)
                }
            LoadResult.Page(
                results,
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
