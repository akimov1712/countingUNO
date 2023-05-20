package com.example.uno.domain.useCases

import com.example.uno.domain.entity.User
import com.example.uno.domain.repository.UnoRepository

class AddUserUseCase(
    private val repository: UnoRepository
) {

    operator fun invoke(user: User){
        return repository.addUserUseCase(user)
    }

}