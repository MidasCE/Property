package com.example.property.di

import android.app.Application
import android.content.Context
import com.example.property.core.SchedulerFactory
import com.example.property.core.SchedulerFactoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideContext(app: Application): Context = app

    @Provides
    @Singleton
    @Named("main")
    fun provideMainScheduler(): Scheduler =  AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    @Named("io")
    fun provideIoScheduler(): Scheduler = Schedulers.io()

    @Provides
    @Singleton
    fun provideSchedulerFactory(@Named("io") ioScheduler : Scheduler,
                                @Named("main") mainScheduler : Scheduler
    ): SchedulerFactory
            = SchedulerFactoryImpl(ioScheduler, mainScheduler)
}
