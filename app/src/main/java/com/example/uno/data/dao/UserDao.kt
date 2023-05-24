package com.example.uno.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.uno.domain.entity.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAllUsers(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("DELETE FROM user")
    fun deleteAllUsers()

}