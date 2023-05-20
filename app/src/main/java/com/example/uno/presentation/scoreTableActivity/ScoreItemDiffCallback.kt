package com.example.uno.presentation.scoreTableActivity

import androidx.recyclerview.widget.DiffUtil
import com.example.uno.domain.entity.Column


class ScoreItemDiffCallback: DiffUtil.ItemCallback<Column>() {

    override fun areItemsTheSame(oldItem: Column, newItem: Column): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Column, newItem: Column): Boolean {
        return oldItem == newItem
    }
}