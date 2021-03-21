package com.test.app

import android.app.Application
import com.test.app.di.AppComponent
import com.test.app.di.ApplicationModule
import com.test.app.di.DaggerAppComponent

class WeatherApp : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        component = DaggerAppComponent.builder().applicationModule(ApplicationModule(this)).build()
    }

    companion object {
        private var instance: WeatherApp? = null
        fun get(): WeatherApp? = instance
    }
}