package com.example.uno.presentation.scoreTableActivity

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uno.R

class ScoreViewHolder(view:View): RecyclerView.ViewHolder(view) {
    val tvNumberRound = view.findViewById<TextView>(R.id.tv_number_round)
    val tvScoreTyomik = view.findViewById<TextView>(R.id.tv_score_tyomik)
    val tvScoreMakson = view.findViewById<TextView>(R.id.tv_score_makson)
    val tvScoreArtem = view.findViewById<TextView>(R.id.tv_score_artem)
    val tvScoreSamurai = view.findViewById<TextView>(R.id.tv_score_samurai)
}