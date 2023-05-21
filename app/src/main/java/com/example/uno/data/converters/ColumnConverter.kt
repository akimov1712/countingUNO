package com.example.uno.data.converters

import androidx.room.TypeConverter
import com.example.uno.domain.entity.Column
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ColumnConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromColumnList(columnList: List<Column>): String {
        return gson.toJson(columnList)
    }

    @TypeConverter
    fun toColumnList(columnListString: String?): List<Column> {
        if (columnListString.isNullOrEmpty()) {
            return emptyList()
        }
        val type = object : TypeToken<List<Column>>() {}.type
        return gson.fromJson(columnListString, type) ?: emptyList()
    }
}