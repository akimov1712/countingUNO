package com.example.uno.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user")
data class User(


    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "countOfWins") var countOfWins: Int,
    @ColumnInfo(name = "scoreOfRecord") var scoreOfRecord: Int,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
) : Serializable
