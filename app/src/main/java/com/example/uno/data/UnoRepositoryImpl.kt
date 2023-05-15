package com.example.uno.data

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.uno.domain.entity.Column
import com.example.uno.domain.entity.Game
import com.example.uno.domain.entity.User
import com.example.uno.domain.repository.UnoRepository

class UnoRepositoryImpl(application: Application) : UnoRepository {

    private val db: AppDatabase = AppDatabase.getInstance(application)

    override fun getAllUsersUseCase(): LiveData<List<User>> {
        return db.userDao().getAllUsers()
    }

    override fun getAllGamesUseCase(): LiveData<List<Game>> {
        return db.gameDao().getAllGames()
    }

    override fun getGameUseCase(id: Int): Game {
        return db.gameDao().getGame(id)
    }

    override fun addColumnUseCase(column: Column) {
        db.columnDao().addColumn(column)
    }
}