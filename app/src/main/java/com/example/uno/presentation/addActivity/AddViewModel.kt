package com.example.uno.presentation.addActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.uno.data.AppDatabase
import com.example.uno.data.UnoRepositoryImpl
import com.example.uno.data.consts.Id
import com.example.uno.data.consts.Names
import com.example.uno.domain.entity.Column
import com.example.uno.domain.entity.Game
import com.example.uno.domain.entity.User
import com.example.uno.domain.useCases.AddColumnUseCase
import com.example.uno.domain.useCases.GetAllUsersUseCase
import com.example.uno.domain.useCases.GetGameUseCase
import com.example.uno.domain.useCases.UpdateUserUseCase

class AddViewModel(database: AppDatabase) : ViewModel() {

    private val repository = UnoRepositoryImpl(database)

    private val getGameUseCase = GetGameUseCase(repository)
    private val addColumnUseCase = AddColumnUseCase(repository)
    private val getAllUsersUseCase = GetAllUsersUseCase(repository)
    private val updateUserUseCase = UpdateUserUseCase(repository)

    private val _gameItem = MutableLiveData<Game>()
    val gameItem: LiveData<Game>
        get() = _gameItem

    val usersList = getAllUsersUseCase.invoke()

    fun addColumn(game: Game, column: Column) {
        editValuesGame(column, game)
        addColumnUseCase.invoke(game, column)
        getGame(game.id)
    }

    private fun editValuesGame(column: Column, game: Game) {
        val users = usersList.value!!
        val maxScoreInList = column.scoreList.maxOrNull() ?: return
        game.maxScore = maxScoreInList
        if (setupWinningUser(game, maxScoreInList, column)) return
        setupCountWins(maxScoreInList, game, users)
    }

     fun setupScoreOfRecord(id: Int, score: Int){
        val users = usersList.value!!
        if (score > users[id].scoreOfRecord) {
            val user = users[id]
            user.scoreOfRecord = score
            updateUserUseCase(user)
        }
    }

    private fun setupWinningUser(
        game: Game,
        maxScoreInList: Int,
        column: Column
    ): Boolean {
        game.winningUser = when (maxScoreInList) {
            column.scoreList[Id.TYOMIK] -> {
                Names.TYOMIK
            }
            column.scoreList[Id.MAKSON] -> {
                Names.MAKSON
            }
            column.scoreList[Id.ARTEM] -> {
                Names.ARTEM
            }
            column.scoreList[Id.SAMURAI] -> {
                Names.SAMURAI
            }
            else -> return true
        }
        return false
    }

    private fun setupCountWins(
        maxScoreInList: Int,
        game: Game,
        users: List<User>
    ) {
        if (maxScoreInList >= game.targetOfScore) {
            game.gameFinish = true
            val winningUser = users.find { it.name == game.winningUser } ?: return
            winningUser.apply {
                countOfWins++
            }
            updateUserUseCase(winningUser)
        }
    }


    fun getGame(id: Int) {
        val gameItem = getGameUseCase.invoke(id)
        _gameItem.value = gameItem

    }
}
