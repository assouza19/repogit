package com.br.repogit.di

import android.app.Application
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(domainModules)
            modules(dataModules)
            modules(presentationModules)
            modules(anotherModules)
        }
    }
}