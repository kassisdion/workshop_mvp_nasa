package com.eldorne.workshop.di

import com.eldorne.workshop.di.modules.AppModule
import com.eldorne.workshop.di.modules.NetworkModule
import com.eldorne.workshop.ui.main.MainActivity
import com.eldorne.workshop.ui.welcome.WelcomeActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
interface WorkshopComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: WelcomeActivity)
}