package com.isak_app.weather.project.data

/**

@Database(entities = [WeatherResponse::class], version = 1)

@TypeConverters(Converters::class)


abstract class AppDatabase : RoomDatabase() {

    abstract fun getDao(): AppDao
}
        */