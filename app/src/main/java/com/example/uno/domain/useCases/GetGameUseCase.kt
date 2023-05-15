package com.example.uno.domain.useCases

import com.example.uno.domain.entity.Game
import com.example.uno.domain.repository.UnoRepository

class GetGameUseCase(
    private val repository: UnoRepository
) {

    operator fun invoke(id: Int): Game {
        TODO()
    }

}