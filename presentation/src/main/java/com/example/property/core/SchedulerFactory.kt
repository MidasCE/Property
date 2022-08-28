package com.example.property.core

import io.reactivex.rxjava3.core.Scheduler

interface SchedulerFactory {
    fun io() : Scheduler

    fun main() : Scheduler
}
