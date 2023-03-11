package com.example.weatherapp.project.modules

import com.example.weatherapp.project.main.Converters
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    private val typeConverter= Converters()




}