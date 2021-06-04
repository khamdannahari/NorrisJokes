package com.khamdannahari.norrisjokes.infrastructure

import android.app.Application
import com.khamdannahari.norrisjokes.infrastructure.di.ApplicationComponent
import com.khamdannahari.norrisjokes.infrastructure.di.ApplicationModule
import com.khamdannahari.norrisjokes.infrastructure.di.DaggerApplicationComponent

class App : Application() {
    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}