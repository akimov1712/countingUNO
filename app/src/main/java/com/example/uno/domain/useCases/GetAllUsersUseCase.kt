package com.example.uno.domain.useCases

import com.example.uno.domain.entity.User
import com.example.uno.domain.repository.UnoRepository

class GetAllUsersUseCase(
    private val repository: UnoRepository
) {

    operator fun invoke(): List<User>{
        TODO()
    }

}