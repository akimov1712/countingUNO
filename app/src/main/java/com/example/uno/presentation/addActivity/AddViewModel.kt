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
import com.example.uno.domain.useCases.AddColumnUseCase
import com.example.uno.domain.useCases.GetGameUseCase

class AddViewModel(database: AppDatabase) : ViewModel() {

    private val repository = UnoRepositoryImpl(database)

    private val getGameUseCase = GetGameUseCase(repository)
    private val addColumnUseCase = AddColumnUseCase(repository)

    private val _gameItem = MutableLiveData<Game>()
    val gameItem: LiveData<Game>
        get() = _gameItem

    private val _finishGame = MutableLiveData<Any>()
    val finishGame: LiveData<Any>
        get() = _finishGame

    fun addColumn(game: Game, column: Column) {
        editValuesGame(column, game)
        addColumnUseCase.invoke(game, column)
        getGame(game.id)
    }

    private fun editValuesGame(column: Column, game: Game) {
        val maxScoreInList = column.scoreList.maxOrNull() ?: return
        game.maxScore = maxScoreInList
        game.winningUser = when (maxScoreInList) {
            column.scoreList[Id.TYOMIK] -> Names.TYOMIK
            column.scoreList[Id.MAKSON] -> Names.MAKSON
            column.scoreList[Id.ARTEM] -> Names.ARTEM
            column.scoreList[Id.SAMURAI] -> Names.SAMURAI
            else -> return
        }
        if (maxScoreInList > game.targetOfScore) {
            game.gameFinish = true
        }
    }


    fun getGame(id: Int) {
        val gameItem = getGameUseCase.invoke(id)
        _gameItem.value = gameItem

    }
}
