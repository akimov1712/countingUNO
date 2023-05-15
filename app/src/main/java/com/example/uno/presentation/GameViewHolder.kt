package com.example.uno.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uno.R

class GameViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val tvNameWinner = view.findViewById<TextView>(R.id.tv_name_winner)
    val tvNumberOfGame = view.findViewById<TextView>(R.id.tv_number_of_game)
    val tvDate = view.findViewById<TextView>(R.id.tv_date)
    val tvTime = view.findViewById<TextView>(R.id.tv_time)
}