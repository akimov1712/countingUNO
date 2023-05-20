package com.example.uno.data.converters

import androidx.lifecycle.MutableLiveData
import androidx.room.TypeConverter
import com.example.uno.domain.entity.Column
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ColumnConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromListColumn(list: List<Column>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toListColumn(json: String): List<Column> {
        val type = object : TypeToken<List<Column>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromMutableLiveData(list: MutableLiveData<List<Column>>): String {
        val value = list.value
        return gson.toJson(value)
    }

    @TypeConverter
    fun toMutableLiveData(json: String): MutableLiveData<List<Column>> {
        val type = object : TypeToken<List<Column>>() {}.type
        val value = gson.fromJson<List<Column>>(json, type)
        return MutableLiveData(value)
    }

}