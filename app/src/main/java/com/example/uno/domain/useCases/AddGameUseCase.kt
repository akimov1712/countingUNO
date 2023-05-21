package com.example.uno.domain.useCases

import com.example.uno.domain.entity.Game
import com.example.uno.domain.repository.UnoRepository

class AddGameUseCase(
    private val repository: UnoRepository
) {

     operator fun invoke(game: Game) {
        return repository.addGameUseCase(game)

    }

}