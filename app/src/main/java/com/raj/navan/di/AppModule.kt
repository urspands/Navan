package com.raj.navan.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.raj.navan.db.LocalPreference
import com.raj.navan.db.LocalPreferenceImpl
import com.raj.navan.db.NewsDao
import com.raj.navan.db.NewsDatabase
import com.raj.navan.repo.NetworkApi
import com.raj.navan.repo.NewsRepository
import com.raj.navan.repo.NewsRepositoryImpl
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
    fun providesNewsRepository(networkApi: NetworkApi, newsDatabase: NewsDatabase,localPreference: LocalPreference): NewsRepository =
        NewsRepositoryImpl(networkApi, newsDatabase,localPreference)

    @Provides
    @Singleton
    fun providesNetworkApi(retrofit: Retrofit): NetworkApi = retrofit.create(NetworkApi::class.java)

    @Provides
    @Singleton
    fun provideNewsDb(@ApplicationContext context: Context): NewsDatabase =
        Room.databaseBuilder(context, NewsDatabase::class.java, "NEWS-DB").build()

    @Provides
    @Singleton
    fun provideNewsDao(newsDatabase: NewsDatabase): NewsDao = newsDatabase.getNewsDao()
    @Provides
    @Singleton
    fun provideLocalPref(@ApplicationContext context: Context): LocalPreference =
        LocalPreferenceImpl(context)
}