package com.fsm.pokedex.data.model

sealed class UiModel {
    data class PokemonItem(val pokemon: Pokemon): UiModel()
    data class SeparatorItem(val description: String): UiModel()
}
