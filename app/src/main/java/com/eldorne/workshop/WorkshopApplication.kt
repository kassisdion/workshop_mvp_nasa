package com.eldorne.workshop

import android.app.Application
import android.content.Context
import com.eldorne.workshop.di.DaggerWorkshopComponent
import com.eldorne.workshop.di.WorkshopComponent
import com.eldorne.workshop.di.modules.AppModule
import com.eldorne.workshop.di.modules.NetworkModule

class WorkshopApplication : Application() {

    /*
    ************************************************************************************************
    ** Singleton
    ************************************************************************************************
    */
    /**
     * Simple singleton used to access to [WorkshopComponent] instance needed for resolving injection
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
        setupComponents()
    }

    /*
    ************************************************************************************************
    ** Private fun
    ************************************************************************************************
    */
    /**
     * Simpli initialise [WorkshopComponent] singleton
     */
    private fun setupComponents() {
        workshopComponent = DaggerWorkshopComponent
                .builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .build()
    }

}
