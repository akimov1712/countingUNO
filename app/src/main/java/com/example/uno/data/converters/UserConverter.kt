package com.example.uno.data.converters

import androidx.room.TypeConverter
import com.example.uno.domain.entity.User
import com.google.gson.Gson

class UserConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromUser(user: User): String {
        return gson.toJson(user)
    }

    @TypeConverter
    fun toUser(userString: String): User {
        return gson.fromJson(userString, User::class.java)
    }

}