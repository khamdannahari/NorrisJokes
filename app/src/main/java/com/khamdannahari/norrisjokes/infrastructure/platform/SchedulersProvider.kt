package com.khamdannahari.norrisjokes.infrastructure.platform

import io.reactivex.Scheduler

interface SchedulersProvider {
    fun getIOScheduler(): Scheduler
    fun getUIScheduler(): Scheduler
}