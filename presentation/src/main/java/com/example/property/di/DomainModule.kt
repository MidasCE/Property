package com.example.app.di

import com.example.domain.interactor.ItemInteractor
import com.example.domain.interactor.ItemInteractorImpl
import com.example.domain.repository.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideItemInteractor(repository: ItemRepository): ItemInteractor =
        ItemInteractorImpl(repository)
}
