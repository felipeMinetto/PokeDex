package com.fsm.pokedex.data.di

import com.fsm.pokedex.api.PokeDexApi
import com.fsm.pokedex.api.PokeDexGraphqlApi
import com.fsm.pokedex.api.adapters.PokemonGQLJsonAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class RESTApi

@Qualifier
annotation class GraphQLApi

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Singleton
    @Provides
    @RESTApi
    fun providesApi(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    @GraphQLApi
    fun providesGraphQLApi(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val moshi = Moshi.Builder()
            .add(PokemonGQLJsonAdapter())
            .build()

        return Retrofit.Builder()
            .baseUrl("https://beta.pokeapi.co/graphql/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun providesApiService(@RESTApi retrofit: Retrofit): PokeDexApi {
        return retrofit.create(PokeDexApi::class.java)
    }

    @Singleton
    @Provides
    fun providesGraphQLApiService(@GraphQLApi retrofit: Retrofit): PokeDexGraphqlApi {
        return retrofit.create(PokeDexGraphqlApi::class.java)
    }
}