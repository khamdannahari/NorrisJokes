package com.khamdannahari.norrisjokes.infrastructure.platform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.khamdannahari.norrisjokes.infrastructure.App
import com.khamdannahari.norrisjokes.infrastructure.di.ApplicationComponent
import kotlinx.android.synthetic.main.activity_base.*

abstract class BaseFragment : Fragment() {

    abstract fun layoutId(): Int

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity?.application as App).appComponent
    }

    private val viewContainer: View get() = (activity as BaseActivity).fragmentContainer

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View =
        inflater.inflate(layoutId(), container, false)

    open fun onBackPressed() {}

    internal fun notify(message: String) =
        Snackbar.make(viewContainer, message, Snackbar.LENGTH_SHORT).show()

    internal fun close() = fragmentManager?.popBackStack()
}
