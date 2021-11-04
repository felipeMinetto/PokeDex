package com.fsm.pokedex.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.fsm.pokedex.R
import com.fsm.pokedex.databinding.ActivityMainBinding
import com.fsm.pokedex.presentation.adapter.PokeLoadStateAdapter
import com.fsm.pokedex.presentation.adapter.PokemonAdapter
import com.fsm.pokedex.presentation.viewmodel.MainViewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewmodel by viewModels<MainViewmodel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val pagingDataAdapter = PokemonAdapter()
        binding.rvPokemons.adapter =
            pagingDataAdapter.withLoadStateHeaderAndFooter(
                header = PokeLoadStateAdapter { pagingDataAdapter.retry() },
                footer = PokeLoadStateAdapter { pagingDataAdapter.retry() })

        lifecycleScope.launch {
            viewmodel.collectPokemons().collect {
                pagingDataAdapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            pagingDataAdapter.loadStateFlow.collect { loadState ->
                val isListEmpty =
                    loadState.refresh is LoadState.NotLoading && pagingDataAdapter.itemCount == 0
                binding.rvPokemons.isVisible = !isListEmpty
                binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            }
        }
    }
}