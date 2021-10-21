package com.fsm.pokedex.data.model

data class Type(
    val id: Int,
    val name: String,
    val names: List<Name>
)
