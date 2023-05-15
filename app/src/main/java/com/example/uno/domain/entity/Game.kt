package com.example.uno.domain.entity

data class Game(

    val id: Int,
    val numberOfGame: Int,
    val targetOfScore: Int,
    val winningUser: User,
    val date: Int,
    val time: Int,
    val listColumns: List<Column>

)