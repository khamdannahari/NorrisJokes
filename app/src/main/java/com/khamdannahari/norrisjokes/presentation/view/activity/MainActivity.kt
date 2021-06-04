package com.khamdannahari.norrisjokes.presentation.view.activity

import android.content.Context
import android.content.Intent
import com.khamdannahari.norrisjokes.infrastructure.platform.BaseActivity
import com.khamdannahari.norrisjokes.infrastructure.platform.BaseFragment
import com.khamdannahari.norrisjokes.presentation.view.fragment.MainFragment

class MainActivity : BaseActivity() {

    companion object {
        fun intent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun fragment(): BaseFragment = MainFragment()
}