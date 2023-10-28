package com.itzik_app.weatherapp.project.main

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication: Application(){
    companion object {
        private var INSTANCE: BaseApplication? = null

        fun getInstance() : Context {
            return INSTANCE!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}