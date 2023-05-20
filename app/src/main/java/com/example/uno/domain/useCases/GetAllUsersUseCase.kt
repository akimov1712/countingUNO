package com.example.uno.domain.useCases

import androidx.lifecycle.LiveData
import com.example.uno.domain.entity.User
import com.example.uno.domain.repository.UnoRepository

class GetAllUsersUseCase(
    private val repository: UnoRepository
) {

    operator fun invoke(): LiveData<List<User>>{
        return repository.getAllUsersUseCase()
    }

}