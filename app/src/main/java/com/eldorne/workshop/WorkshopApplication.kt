package com.eldorne.workshop

import android.app.Application
import android.content.Context
import com.eldorne.workshop.di.modules.AppModule
import com.eldorne.workshop.di.DaggerWorkshopComponent
import com.eldorne.workshop.di.modules.NetworkModule

import com.eldorne.workshop.di.WorkshopComponent

class WorkshopApplication : Application() {

    /*
    ************************************************************************************************
    ** Singleton
    ************************************************************************************************
    */
    companion object {
        lateinit var workshopComponent: WorkshopComponent

        operator fun get(context: Context): WorkshopApplication {
            return context.applicationContext as WorkshopApplication
        }
    }

    /*
    ************************************************************************************************
    ** Life cycle
    ************************************************************************************************
    */
    override fun onCreate() {
        super.onCreate()

        workshopComponent = DaggerWorkshopComponent
                .builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .build()
    }
}
