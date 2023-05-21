package com.example.uno.domain.useCases

import com.example.uno.domain.entity.Column
import com.example.uno.domain.entity.Game
import com.example.uno.domain.repository.UnoRepository

class AddColumnUseCase(
    private val repository: UnoRepository
) {

    operator fun invoke(game: Game, column: Column) {

        repository.addColumnUseCase(game, column)

    }

}