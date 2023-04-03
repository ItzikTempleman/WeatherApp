package com.example.weatherapp.project.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import com.example.weatherapp.project.models.WeatherResponse

/**

@Database(entities = [WeatherResponse::class], version = 1)

@TypeConverters(Converters::class)


abstract class AppDatabase : RoomDatabase() {

    abstract fun getDao(): AppDao
}
        */