package com.example.uno.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.uno.domain.entity.User

class UserItemDiffCallback: DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}