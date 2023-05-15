package com.example.uno.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.uno.data.dao.ColumnDao
import com.example.uno.data.dao.GameDao
import com.example.uno.data.dao.UserDao
import com.example.uno.domain.entity.Column
import com.example.uno.domain.entity.Game
import com.example.uno.domain.entity.User

@Database(entities = [Column::class, Game::class, User::class], version = 1, exportSchema = false)
abstract class AppDatabase:RoomDatabase(){

    abstract fun columnDao(): ColumnDao
    abstract fun gameDao(): GameDao
    abstract fun userDao(): UserDao

    companion object{
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "uno.db"

        fun getInstance(application: Application):AppDatabase{
            synchronized(LOCK){
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(application, AppDatabase::class.java, DB_NAME).build()
                INSTANCE = db
                return db
            }
        }

    }

}