package com.example.uno.domain.useCases

import androidx.lifecycle.LiveData
import com.example.uno.domain.entity.Game
import com.example.uno.domain.repository.UnoRepository

class GetAllGamesUseCase(
    private val repository: UnoRepository
) {

    operator fun invoke(): LiveData<List<Game>>{
        return repository.getAllGamesUseCase()

    }

}