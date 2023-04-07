package com.raj.navan.di

import android.content.Context
import androidx.room.Room
import com.raj.navan.repo.NetworkApi
import com.raj.navan.repo.NewsRepository
import com.raj.navan.repo.NewsRepositoryImpl
import com.raj.navan.repo.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun providesNewsRepository(networkApi: NetworkApi, newsDatabase: NewsDatabase): NewsRepository =
        NewsRepositoryImpl(networkApi, newsDatabase.newsDoa())

    @Provides
    @Singleton
    fun providesNetworkApi(retrofit: Retrofit): NetworkApi = retrofit.create(NetworkApi::class.java)

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): NewsDatabase =
        Room.databaseBuilder(context, NewsDatabase::class.java, "News").build()
}