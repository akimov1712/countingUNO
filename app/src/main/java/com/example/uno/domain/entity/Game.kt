package com.example.uno.domain.entity

import androidx.lifecycle.MutableLiveData
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.uno.data.consts.Names

@Entity(tableName = "game")
data class Game(

    @ColumnInfo(name = "targetOfScore") val targetOfScore: Int,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "time") val time: String,
    @ColumnInfo(name = "maxScore") val maxScore: Int = 0,
    @ColumnInfo(name = "winningUser") val winningUser: String = Names.TYOMIK,
    @ColumnInfo(name = "listColumns") val listColumns: MutableLiveData<List<Column>> = MutableLiveData(),
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0

)