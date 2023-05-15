package com.example.uno.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.uno.R
import com.example.uno.domain.entity.Game

class GameAdapter: ListAdapter<Game, GameViewHolder>(GameItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val layout = when(viewType){
            VIEW_TYPE_ACTIVE -> R.layout.item_game_active
            VIEW_TYPE_DISACTIVE -> R.layout.item_game_disactive
            else -> throw RuntimeException("viewType not found: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout,parent,false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val gameItem = getItem(position)
        with(holder){
            tvNameWinner.text = gameItem.winningUser.name
            tvNumberOfGame.text = gameItem.numberOfGame.toString()
            tvDate.text = gameItem.date.toString()
            tvTime.text = gameItem.time.toString()
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.id == itemCount){
            VIEW_TYPE_ACTIVE
        } else{
            VIEW_TYPE_DISACTIVE
        }
    }

    companion object{
        const val VIEW_TYPE_ACTIVE = 1
        const val VIEW_TYPE_DISACTIVE = 0
        const val MAX_POOL_SIZE = 0

    }

}