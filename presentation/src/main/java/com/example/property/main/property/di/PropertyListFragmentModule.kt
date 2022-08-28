package com.example.property.main.property.di

import com.example.property.main.property.viewmodel.PropertyListViewModel
import com.example.property.core.SchedulerFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
class PropertyListFragmentModule {

    @Provides
    fun providePropertyListViewModel(
        schedulerFactory: SchedulerFactory
    ): PropertyListViewModel =
        PropertyListViewModel(
            schedulerFactory
        )

}
