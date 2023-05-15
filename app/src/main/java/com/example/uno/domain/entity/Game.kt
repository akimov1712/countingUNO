package com.example.uno.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class Game(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val numberOfGame: Int,
    val targetOfScore: Int,
    val winningUser: User,
    val date: Int,
    val time: Int,
    val listColumns: List<Column>

)