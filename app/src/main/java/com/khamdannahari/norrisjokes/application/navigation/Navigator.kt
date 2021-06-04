package com.khamdannahari.norrisjokes.application.navigation

import androidx.fragment.app.FragmentActivity
import com.khamdannahari.norrisjokes.presentation.view.activity.MainActivity
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Navigator @Inject constructor() {

    fun showMain(activity: FragmentActivity) {
        activity.startActivity(MainActivity.intent(activity))
    }

}
