package com.example.uno.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.uno.domain.entity.Game
import com.example.uno.domain.entity.User

class GameItemDiffCallback: DiffUtil.ItemCallback<Game>() {

    override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
        return oldItem == newItem
    }
}