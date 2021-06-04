package com.khamdannahari.norrisjokes.application.service

import android.content.Context
import com.khamdannahari.norrisjokes.R
import com.khamdannahari.norrisjokes.application.exception.NetworkConnectionException
import com.khamdannahari.norrisjokes.application.exception.ServerErrorException
import javax.inject.Inject
import javax.inject.Singleton

/*
 * Format localized common error messages
 */
@Singleton
class ErrorMessageFormatter @Inject constructor(context: Context) {

    private val r = context.resources

    fun getErrorMessage(e: Throwable): String =
        when (e) {
            is NetworkConnectionException -> r.getString(R.string.failure_network_connection)
            is ServerErrorException -> r.getString(R.string.failure_server_error)
            else -> r.getString(R.string.failure_unknown_error, e.localizedMessage)
        }
}