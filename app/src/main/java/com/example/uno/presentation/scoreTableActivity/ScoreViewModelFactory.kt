package com.example.uno.presentation.scoreTableActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.uno.data.AppDatabase

class ScoreViewModelFactory(private val database: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScoreViewModel::class.java)) {
            return ScoreViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}