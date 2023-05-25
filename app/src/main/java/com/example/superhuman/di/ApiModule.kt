package com.example.superhuman.di

import com.example.superhuman.data.HealthApi
import com.example.superhuman.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule
{

    @Provides
    @Singleton
    fun providesRetrofit():Retrofit
    {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesHealthApi(retrofit:Retrofit): HealthApi
    {
        return retrofit.create(HealthApi::class.java)
    }

}