package com.example.uno.presentation.scoreTableActivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.uno.R
import com.example.uno.domain.entity.Column

class ScoreAdapter: ListAdapter<Column,ScoreViewHolder>(ScoreItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_score, parent,false)
        return ScoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        val scoreItem = getItem(position)
        with(holder){
            tvNumberRound.text = "Раунд ${scoreItem.id}"
            tvScoreTyomik.text = scoreItem.scoreTyomik.toString()
            tvScoreMakson.text = scoreItem.scoreMakson.toString()
            tvScoreArtem.text = scoreItem.scoreArtem.toString()
            tvScoreSamurai.text = scoreItem.scoreSamurai.toString()
        }
    }
}