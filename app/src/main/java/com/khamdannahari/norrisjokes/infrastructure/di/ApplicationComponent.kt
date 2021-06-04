package com.khamdannahari.norrisjokes.infrastructure.di

import com.khamdannahari.norrisjokes.application.navigation.RouteActivity
import com.khamdannahari.norrisjokes.presentation.view.dialog.SearchDialog
import com.khamdannahari.norrisjokes.presentation.view.fragment.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(routeActivity: RouteActivity)
    fun inject(mainFragment: MainFragment)
    fun inject(searchDialog: SearchDialog)
}