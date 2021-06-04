package com.khamdannahari.norrisjokes.infrastructure.platform

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.khamdannahari.norrisjokes.R
import com.khamdannahari.norrisjokes.infrastructure.App
import com.khamdannahari.norrisjokes.infrastructure.di.ApplicationComponent
import kotlinx.android.synthetic.main.activity_base.fragmentContainer

abstract class BaseDialog : DialogFragment() {

    abstract fun layoutId(): Int

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity?.application as App).appComponent
    }

    private val viewContainer: View get() = (activity as BaseActivity).fragmentContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NO_FRAME, R.style.Screen75PercentDialog)
    }

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View  {
        setupPosition()
        return inflater.inflate(layoutId(), container, false)
    }

    private fun setupPosition() {

        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.window?.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.TOP)
        val p = dialog?.window?.attributes
        p?.width = ViewGroup.LayoutParams.MATCH_PARENT
        p?.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE
        dialog?.window?.attributes = p
    }

    open fun onBackPressed() {}

    internal fun notify(message: String) =
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    internal fun close() = fragmentManager?.popBackStack()
}
