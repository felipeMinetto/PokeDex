package com.fsm.pokedex.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Name(
    val name: String,
    val language: Language
)
