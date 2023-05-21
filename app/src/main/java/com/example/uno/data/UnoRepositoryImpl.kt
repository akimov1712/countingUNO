package com.example.uno.data

import androidx.lifecycle.LiveData
import com.example.uno.domain.entity.Column
import com.example.uno.domain.entity.Game
import com.example.uno.domain.entity.User
import com.example.uno.domain.repository.UnoRepository

class UnoRepositoryImpl(private val db: AppDatabase) : UnoRepository {


     override fun getAllUsersUseCase(): LiveData<List<User>> {
        return db.userDao().getAllUsers()

    }

    override  fun getAllGamesUseCase(): LiveData<List<Game>> {
        return db.gameDao().getAllGames()

    }

    override fun getGameUseCase(id: Int): Game {
        return db.gameDao().getGame(id)

    }

    override fun addColumnUseCase(game: Game, column: Column) {
        val newGame = game.copy()
        val newColumnList = newGame.listColumns.toMutableList()
        newColumnList.add(column)
        newGame.listColumns = newColumnList
        db.gameDao().addGame(newGame)
    }

    override fun addGameUseCase(game: Game) {
        db.gameDao().addGame(game)
    }

    override fun addUserUseCase(user: User) {
        db.userDao().addUser(user)
    }

    init {
        addUserUseCase(User(name = "Тьомик", countOfWins = 0, scoreOfRecord = 0, id = 1))
        addUserUseCase(User(name = "Максон", countOfWins = 0, scoreOfRecord = 0,id = 2))
        addUserUseCase(User(name = "Артем", countOfWins = 0, scoreOfRecord = 0,id = 3))
        addUserUseCase(User(name = "Самурай", countOfWins = 0, scoreOfRecord = 0,id = 4))
    }

}