package com.example.uno.data

import androidx.lifecycle.LiveData
import com.example.uno.data.consts.Id
import com.example.uno.domain.entity.Column
import com.example.uno.domain.entity.Game
import com.example.uno.data.consts.Names
import com.example.uno.domain.entity.User
import com.example.uno.domain.repository.UnoRepository

class UnoRepositoryImpl(private val db: AppDatabase) : UnoRepository {


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

    override fun addGameUseCase(game: Game) {
        db.gameDao().addGame(game)
    }

    override fun addUserUseCase(user: User) {
        db.userDao().addUser(user)
    }

    init {
        addUserUseCase(User(name = "Тьомик", countOfWins = 0, scoreOfRecord = 0,id = Id.TYOMIK))
        addUserUseCase(User(name = "Максон", countOfWins = 0, scoreOfRecord = 0,id = Id.MAKSON))
        addUserUseCase(User(name = "Артем", countOfWins = 0, scoreOfRecord = 0,id = Id.ARTEM))
        addUserUseCase(User(name = "Самурай", countOfWins = 0, scoreOfRecord = 0,id = Id.SAMURAI))
    }

}