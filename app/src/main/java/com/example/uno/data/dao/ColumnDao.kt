package com.example.uno.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.uno.domain.entity.Column


@Dao
interface ColumnDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addColumn(column: Column)

}