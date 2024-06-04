package com.yashv.anime_guide.presentation.di

import com.yashv.anime_guide.data.remote.AnimeApiService
import com.yashv.anime_guide.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkApiModule {

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
//        val logging = HttpLoggingInterceptor()
//        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)

        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                val newRequest = request.newBuilder()
                    .header("X-RapidAPI-Key", Constants.RAPID_KEY)
                    .header("X-RapidAPI-Host", Constants.RAPID_HOST)
                chain.proceed(newRequest.build())
            }
//            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesAnimeApiService(retrofit: Retrofit): AnimeApiService {
        return retrofit.create(AnimeApiService::class.java)
    }
}