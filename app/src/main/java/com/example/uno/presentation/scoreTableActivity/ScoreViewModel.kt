package com.example.uno.presentation.scoreTableActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.uno.data.AppDatabase
import com.example.uno.data.UnoRepositoryImpl
import com.example.uno.domain.entity.Column
import com.example.uno.domain.entity.Game
import com.example.uno.domain.useCases.*
import java.text.SimpleDateFormat
import java.util.*

class ScoreViewModel(database: AppDatabase): ViewModel() {

    private val repository = UnoRepositoryImpl(database)

    private val getGameUseCase = GetGameUseCase(repository)
    private val addColumnUseCase = AddColumnUseCase(repository)

    private val _gameItem = MutableLiveData<Game>()
    val gameItem: LiveData<Game>
    get() = _gameItem

    private val _gameColumn = MutableLiveData<List<Column>>()
    val gameColumn: LiveData<List<Column>>
        get() = _gameColumn

    fun addColumn(column: Column){
        addColumnUseCase.invoke(column)
    }

    fun getGameColumn(game: Game){
        _gameColumn.value = game.listColumns.value
    }

    fun getGame(id: Int){
        val gameItem = getGameUseCase.invoke(id)
        _gameItem.value = gameItem
    }

    private val _shouldCloseActivity = MutableLiveData<Unit>()
    val shouldCloseActivity: LiveData<Unit>
        get() = _shouldCloseActivity

    private fun finishActivity() {
        _shouldCloseActivity.value = Unit
    }
}