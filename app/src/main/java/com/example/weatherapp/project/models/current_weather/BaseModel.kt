package com.example.weatherapp.project.models.current_weather

import androidx.room.PrimaryKey

open class BaseModel(@PrimaryKey(autoGenerate = true) var id: Int=0)