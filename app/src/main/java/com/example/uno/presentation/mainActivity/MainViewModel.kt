package com.example.uno.presentation.mainActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.uno.data.AppDatabase
import com.example.uno.data.UnoRepositoryImpl
import com.example.uno.domain.entity.Game
import com.example.uno.domain.useCases.AddGameUseCase
import com.example.uno.domain.useCases.GetAllGamesUseCase
import com.example.uno.domain.useCases.GetAllUsersUseCase
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(database: AppDatabase): ViewModel() {

    private val repository = UnoRepositoryImpl(database)

    private val getAllGamesUseCase = GetAllGamesUseCase(repository)
    private val getAllUsersUseCase = GetAllUsersUseCase(repository)
    private val addGameUseCase = AddGameUseCase(repository)

    private val _shouldCloseModal = MutableLiveData<Unit>()
    val shouldCloseModal: LiveData<Unit>
        get() = _shouldCloseModal

    val getGamesList = getAllGamesUseCase.invoke()
    val getUsersList = getAllUsersUseCase.invoke()

    fun addCreateGame(inputTarget: String?) {
        val target = parseTarget(inputTarget)
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        val game = Game(target,currentDate,currentTime)
        addGameUseCase.invoke(game)
        finishModal()
    }

    private fun parseTarget(inputTarget: String?): Int {
        return try {
            inputTarget?.trim()?.toInt() ?: 600
        } catch (ex: Exception) {
            600
        }
    }

    private fun finishModal() {
        _shouldCloseModal.value = Unit
    }

}