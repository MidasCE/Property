package com.example.property.core

import io.reactivex.rxjava3.core.Scheduler

class SchedulerFactoryImpl(
    private val ioScheduler: Scheduler,
    private val mainScheduler: Scheduler
) : SchedulerFactory {
    override fun io(): Scheduler = ioScheduler

    override fun main(): Scheduler = mainScheduler
}
