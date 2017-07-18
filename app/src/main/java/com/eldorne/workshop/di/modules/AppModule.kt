package com.eldorne.workshop.di.modules

import android.app.Application
import android.content.Context
import com.eldorne.workshop.WorkshopApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: WorkshopApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideApplication(): Application = app
}