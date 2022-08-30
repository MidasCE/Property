package com.example.property.main.property.di

import com.example.domain.interactor.ItemInteractor
import com.example.property.main.property.viewmodel.PropertyListViewModel
import com.example.property.core.SchedulerFactory
import com.example.property.main.property.PropertyItemAdapter
import com.example.property.main.property.PropertyListFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
class PropertyListFragmentModule {

    @Provides
    fun providePropertyListViewModel(
        schedulerFactory: SchedulerFactory,
        itemInteractor: ItemInteractor
    ): PropertyListViewModel =
        PropertyListViewModel(
            schedulerFactory,
            itemInteractor
        )

    @Provides
    fun providePropertyItemAdapter(
    ): PropertyItemAdapter =
        PropertyItemAdapter()

}
