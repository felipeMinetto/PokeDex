package com.fsm.pokedex.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Stat(
    val id: Int?,
    val name: String,
    val names: List<Name>?
)
