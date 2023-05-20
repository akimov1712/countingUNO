package com.example.uno.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "countOfWins") val countOfWins: Int,
    @ColumnInfo(name = "scoreOfRecord") val scoreOfRecord: Int,

)
