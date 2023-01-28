package com.keepcoding.navi.marvelapp.di

import com.keepcoding.navi.marvelapp.data.mappers.toMD5Hash
import com.keepcoding.navi.marvelapp.data.remote.MarvelApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val publicKey = "ab19437d9e0c64db15e1cfde58bda5ff"
    private val privateKey = "2e0e92a65292dbbc2fb8262598c5ed12ece043ee"

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder().apply {
                    header("Content-Type", "Application/Json")
                }.build()
                chain.proceed(newRequest)
            }
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor { chain ->
                val ts = System.currentTimeMillis().toString()
                val accessString = "$ts$privateKey$publicKey"
                val httpUrl = chain.request().url.newBuilder()
                    .addQueryParameter("ts", ts)
                    .addQueryParameter("apikey", publicKey)
                    .addQueryParameter("hash", accessString.toMD5Hash())
                    .build()
                val builder = chain.request().newBuilder().url(httpUrl)
                chain.proceed(builder.build())
            }
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://gateway.marvel.com/v1/public/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build()
    }

    @Provides
    fun provideAPI(retrofit: Retrofit): MarvelApi {
        return retrofit.create(MarvelApi::class.java)
    }

}