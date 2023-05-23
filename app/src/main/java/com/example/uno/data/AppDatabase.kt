package com.example.uno.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.uno.data.converters.ColumnConverter
import com.example.uno.data.converters.UserConverter
import com.example.uno.data.dao.GameDao
import com.example.uno.data.dao.UserDao
import com.example.uno.domain.entity.Game
import com.example.uno.domain.entity.User

@Database(
    entities = [
        Game::class,
        User::class,],
    version = 7,
    exportSchema = false
)
@TypeConverters(UserConverter::class, ColumnConverter::class)
abstract class AppDatabase:RoomDatabase(){
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