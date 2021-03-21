package com.test.app.di

import com.test.app.BaseTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(TestModule::class))
interface TestComponent {
    fun inject(test: BaseTest)
}