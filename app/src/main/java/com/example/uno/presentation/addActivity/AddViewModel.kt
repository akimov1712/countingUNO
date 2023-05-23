package com.example.uno.presentation.addActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.uno.data.AppDatabase
import com.example.uno.data.UnoRepositoryImpl
import com.example.uno.domain.entity.Column
import com.example.uno.domain.entity.Game
import com.example.uno.domain.useCases.AddColumnUseCase
import com.example.uno.domain.useCases.GetGameUseCase

class AddViewModel(database: AppDatabase): ViewModel() {

    private val repository = UnoRepositoryImpl(database)

    private val getGameUseCase = GetGameUseCase(repository)
    private val addColumnUseCase = AddColumnUseCase(repository)

    private val _gameItem = MutableLiveData<Game>()
    val gameItem: LiveData<Game>
        get() = _gameItem

    fun addColumn(game: Game, column: Column) {
        addColumnUseCase.invoke(game, column)
        getGame(game.id)
    }

    fun getGame(id: Int) {
        val gameItem = getGameUseCase.invoke(id)
        _gameItem.value = gameItem

    }
}
