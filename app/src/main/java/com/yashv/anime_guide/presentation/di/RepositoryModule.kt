package com.yashv.anime_guide.presentation.di

import com.yashv.anime_guide.data.remote.AnimeApiService
import com.yashv.anime_guide.data.repository.AnimeRepositoryImpl
import com.yashv.anime_guide.domain.repository.AnimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesAnimeRepository(animeApiService: AnimeApiService): AnimeRepository {
        return AnimeRepositoryImpl(animeApiService)
    }
}