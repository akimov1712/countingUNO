package com.example.uno.data

import android.content.Context
import androidx.room.*
import com.example.uno.data.converters.ColumnConverter
import com.example.uno.data.converters.UserConverter
import com.example.uno.data.dao.ColumnDao
import com.example.uno.data.dao.GameDao
import com.example.uno.data.dao.UserDao
import com.example.uno.domain.entity.Column
import com.example.uno.domain.entity.Game
import com.example.uno.domain.entity.User

@Database(
    entities = [
        Game::class,
        User::class,
        Column::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(UserConverter::class, ColumnConverter::class)
abstract class AppDatabase:RoomDatabase(){

    abstract fun columnDao(): ColumnDao
    abstract fun gameDao(): GameDao
    abstract fun userDao(): UserDao

    companion object{
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "unos.db"

        fun getInstance(context: Context):AppDatabase{
            synchronized(LOCK){
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = db
                return db
            }
        }

    }

}