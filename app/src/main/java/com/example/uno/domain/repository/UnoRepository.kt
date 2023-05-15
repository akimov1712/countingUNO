package com.example.uno.domain.repository

import com.example.uno.domain.entity.Column
import com.example.uno.domain.entity.Game
import com.example.uno.domain.entity.User

interface UnoRepository {

    fun getAllUsersUseCase(): List<User>

    fun getAllGamesUseCase(): List<Game>

    fun getGameUseCase(id: Int): Game

    fun addColumnUseCase(column: Column)

}