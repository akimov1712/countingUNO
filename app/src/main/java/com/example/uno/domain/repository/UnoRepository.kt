package com.example.uno.domain.repository

import androidx.lifecycle.LiveData
import com.example.uno.domain.entity.Column
import com.example.uno.domain.entity.Game
import com.example.uno.domain.entity.User

interface UnoRepository {

    fun getAllUsersUseCase(): LiveData<List<User>>

    fun getAllGamesUseCase(): LiveData<List<Game>>

    fun getGameUseCase(id: Int): Game

    fun addColumnUseCase(column: Column)

    fun addGameUseCase(game: Game)

    fun addUserUseCase(user: User)

}