package com.example.uno.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "column")
data class Column(

    @PrimaryKey(autoGenerate = true)
    val numberOfColumn: Int,
    val scoreTyomik: Int,
    val scoreMakson: Int,
    val scoreArtem: Int,
    val scoreSamurai: Int,

)
