package com.mobile.fair.di

import android.content.Context
import com.mobile.fair.data.local.CandidateDao
import com.mobile.fair.data.local.CandidateDatabase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mobile.fair.data.api.CandidateRemoteDataSource
import com.mobile.fair.data.api.CandidateService
import com.mobile.fair.data.repository.CandidateRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://dummyapi.io/data/v1/")
        .client(
            OkHttpClient.Builder().addInterceptor { chain ->
                val request: Request =
                    chain.request().newBuilder().addHeader("app-id", "6143418364f8795e27d7b785")
                        .build()
                chain.proceed(request)
            }.build()
        )
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCandidateService(retrofit: Retrofit): CandidateService = retrofit.create(
        CandidateService::class.java
    )

    @Singleton
    @Provides
    fun provideCandidateRemoteDataSource(candidateService: CandidateService) =
        CandidateRemoteDataSource(candidateService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        CandidateDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCandidateDao(db: CandidateDatabase) = db.candidateDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: CandidateRemoteDataSource,
        localDataSource: CandidateDao
    ) =
        CandidateRepository(remoteDataSource, localDataSource)
}