package com.example.android.heysports.di.net

import android.content.Context
import androidx.databinding.ktx.BuildConfig
import com.example.android.heysports.HeyApplication
import com.example.android.heysports.R
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Jihye Noh
 * Date: 2022-02-17
 */
@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    @Named("YoutubeSearch")
    fun getRetrofitClient(gson: Gson, @ApplicationContext context: Context): Retrofit =
        Retrofit.Builder()
            .client(getOkHttpClient())
            .baseUrl(HeyApplication.getContext().getString(R.string.youtube_api))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(getGson()))
            .build()

    @Provides
    @Singleton
    fun getGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun getOkHttpClient(): OkHttpClient = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    } else {
        OkHttpClient.Builder().build()
    }
}