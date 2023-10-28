package com.isak_app.weather.project.models

import androidx.room.PrimaryKey

open class BaseModel(@PrimaryKey(autoGenerate = true) var id: Int=0)