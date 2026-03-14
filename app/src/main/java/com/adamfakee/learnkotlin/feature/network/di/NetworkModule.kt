package com.adamfakee.learnkotlin.feature.home.di

import com.adamfakee.learnkotlin.feature.home.data.SocialApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Cung cấp ApiService riêng tư cho Feature Home/Social
    @Provides
    @Singleton
    fun provideSocialApiService(retrofit: Retrofit): SocialApiService {
        return retrofit.create(SocialApiService::class.java)
    }
}
