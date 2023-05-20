package com.example.uno.domain.useCases

import com.example.uno.domain.entity.Column
import com.example.uno.domain.repository.UnoRepository

class AddColumnUseCase(
    private val repository: UnoRepository
) {

    operator fun invoke(column: Column){
        repository.addColumnUseCase(column)
    }

}