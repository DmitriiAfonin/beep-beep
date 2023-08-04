package com.beepbeep

import android.app.Application
import di.appModule
import org.koin.core.context.startKoin


class BeebBeebApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                appModule(),
            )
        }
    }
}