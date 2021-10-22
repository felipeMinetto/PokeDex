package com.fsm.pokedex.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.fsm.pokedex.R
import com.fsm.pokedex.databinding.ActivityMainBinding
import com.fsm.pokedex.view.adapter.PokemonAdapter
import com.fsm.pokedex.viewmodel.MainViewmodel
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
        binding.rvPokemons.adapter = pagingDataAdapter

        lifecycleScope.launch {
            viewmodel.collectPokemons().collect {
                pagingDataAdapter.submitData(it)
            }
        }
    }
}