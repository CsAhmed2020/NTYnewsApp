package com.example.nytnews.di

import com.example.nytnews.data.remote.NewsApi
import com.example.nytnews.data.repository.NewsRepositoryImpl
import com.example.nytnews.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object AppModule {

    val baseUrl = "https://api.nytimes.com/svc/archive/v1/"

    @Singleton
    @Provides
    fun getRetrofitBuilder():Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getNewsApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Singleton
    @Provides
    fun getNewsRepository(newsApi: NewsApi): NewsRepository {
        return NewsRepositoryImpl(newsApi)
    }
}