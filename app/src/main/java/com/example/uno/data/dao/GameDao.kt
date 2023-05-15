package com.example.uno.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.uno.domain.entity.Game

@Dao
interface GameDao {

    @Query("SELECT * FROM game")
    fun getAllGames(): LiveData<List<Game>>

    @Query("SELECT * FROM game WHERE id=:id LIMIT 1")
    fun getGame(id: Int):Game

}