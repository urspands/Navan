package com.raj.navan.di

import com.raj.navan.repo.NetworkApi
import com.raj.navan.repo.NewsRepository
import com.raj.navan.repo.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(NetworkApi.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun providesNewsRepository(networkApi: NetworkApi): NewsRepository =
        NewsRepositoryImpl(networkApi)

    @Provides
    @Singleton
    fun providesNetworkApi(retrofit: Retrofit): NetworkApi = retrofit.create(NetworkApi::class.java)
}