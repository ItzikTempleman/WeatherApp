package com.itzik_app.weatherapp.project.data

/**

@Database(entities = [WeatherResponse::class], version = 1)

@TypeConverters(Converters::class)


abstract class AppDatabase : RoomDatabase() {

    abstract fun getDao(): AppDao
}
        */