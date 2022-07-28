package com.example.android_imperative.di

import android.app.Application
import com.example.android_imperative.db.AppDataBase
import com.example.android_imperative.db.TVShowDao
import com.example.android_imperative.network.Server.IS_TESTER
import com.example.android_imperative.network.Server.SERVER_DEVELOPMENT
import com.example.android_imperative.network.Server.SERVER_PRODUCTION
import com.example.android_imperative.network.TVShowService
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

    /**
     * Retrofit Related
     */
    @Provides
    fun server(): String {
        if (IS_TESTER) return SERVER_DEVELOPMENT
        return SERVER_PRODUCTION
    }

    @Provides
    @Singleton
    fun retrofitClient(): Retrofit {
        return Retrofit.Builder().baseUrl(server())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun tvShowService(): TVShowService {
        return retrofitClient().create(TVShowService::class.java)
    }

    /**
     * Room Related
     */

    @Provides
    @Singleton
    fun appDatabase(context: Application): AppDataBase {
        return AppDataBase.getAppDBInstance(context)
    }

    @Provides
    @Singleton
    fun tvShowDao(appDatabase: AppDataBase): TVShowDao {
        return appDatabase.getTVShowDao()
    }
}