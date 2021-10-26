package com.fsm.pokedex.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fsm.pokedex.R
import com.fsm.pokedex.databinding.SeparatorViewItemBinding

class SeparatorViewHolder(
    private val binding: SeparatorViewItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(separatorText: String) {
        binding.generationName = separatorText
    }

    companion object {
        fun create(parent: ViewGroup): SeparatorViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.separator_view_item, parent, false)
            return SeparatorViewHolder(SeparatorViewItemBinding.bind(view))
        }
    }
}