package com.khamdannahari.norrisjokes.common

import com.khamdannahari.norrisjokes.infrastructure.platform.SchedulersProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/*
 * Provide IO schedulers for RxJava for testing
 */
class SchedulersProviderTest: SchedulersProvider {
    override fun getIOScheduler(): Scheduler = Schedulers.trampoline()
    override fun getUIScheduler(): Scheduler = Schedulers.trampoline()
}