package com.fsm.pokedex.data.model

import com.squareup.moshi.Json

data class Language(
    val id: Int,
    val name: String,
    val official: Boolean,
    @Json(name = "iso639") val country: String,
    @Json(name = "iso3166") val language: String,
    val names: List<Name>
)
