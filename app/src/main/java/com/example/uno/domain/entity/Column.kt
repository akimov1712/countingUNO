package com.example.uno.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "column")
data class Column(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "scoreTyomik") val scoreTyomik: Int,
    @ColumnInfo(name = "scoreMakson") val scoreMakson: Int,
    @ColumnInfo(name = "scoreArtem") val scoreArtem: Int,
    @ColumnInfo(name = "scoreSamurai") val scoreSamurai: Int,

    )
