package com.example.uno.presentation.mainActivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.uno.R
import com.example.uno.domain.entity.User

class UserAdapter: ListAdapter<User, UserViewHolder>(UserItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layout = R.layout.item_user
        val view = LayoutInflater.from(parent.context).inflate(layout, parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val userItem = getItem(position)
        with(holder) {
            tvName.text = userItem.name
            tvScoreOfRecord.text = userItem.scoreOfRecord.toString()
            tvUserCountOfWins.text = userItem.countOfWins.toString()
        }
    }
}