package com.example.uno.domain.useCases

import com.example.uno.domain.entity.User
import com.example.uno.domain.repository.UnoRepository

class UpdateUserUseCase(
    private val repository: UnoRepository
) {

    operator fun invoke(user: User){
        return repository.updateUserUseCase(user)

    }

}