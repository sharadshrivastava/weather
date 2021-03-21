package com.test.app.di

import com.test.app.ui.home.HomeViewModel
import com.test.app.ui.recent.RecentSearchViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ServiceModule::class, DBModule::class, ApplicationModule::class))
interface AppComponent {

    fun inject(vm: HomeViewModel)

    fun inject(vm: RecentSearchViewModel)
}