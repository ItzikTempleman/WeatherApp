package com.itzik_app.weatherapp.project.models

import androidx.room.PrimaryKey

open class BaseModel(@PrimaryKey(autoGenerate = true) var id: Int=0)