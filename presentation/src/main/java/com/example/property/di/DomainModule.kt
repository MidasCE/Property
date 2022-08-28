package com.example.app.di

import com.example.domain.interactor.ItemInteractor
import com.example.domain.interactor.ItemInteractorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import repository.comic.ComicRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideComicInteractor(repository: ComicRepository): ItemInteractor =
        ItemInteractorImpl(repository)
}
