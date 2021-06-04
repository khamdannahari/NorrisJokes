package com.khamdannahari.norrisjokes.application.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.khamdannahari.norrisjokes.infrastructure.App
import com.khamdannahari.norrisjokes.infrastructure.di.ApplicationComponent
import javax.inject.Inject

/*
 * Main entry point of the application
 */
class RouteActivity : AppCompatActivity() {

    private val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as App).appComponent
    }

    @Inject
    internal lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        navigator.showMain(this)
    }
}