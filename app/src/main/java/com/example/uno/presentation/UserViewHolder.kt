package com.example.uno.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uno.R

class UserViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val tvName = view.findViewById<TextView>(R.id.tv_user_name)
    val tvUserCountOfWins = view.findViewById<TextView>(R.id.tv_user_count_of_wins)
    val tvScoreOfRecord = view.findViewById<TextView>(R.id.tv_user_score_of_record)

}