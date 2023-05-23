package com.example.uno.presentation.addActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.uno.data.AppDatabase

class AddViewModelFactory(private val database: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddViewModel::class.java)) {
            return AddViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}