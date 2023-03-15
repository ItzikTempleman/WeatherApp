package com.example.weatherapp.project.main

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication: Application(){


    init {
        instance = this
    }

    companion object {
        private var instance: BaseApplication? = null

        fun getContext() : Context {
            return instance!!.applicationContext
        }
    }
}