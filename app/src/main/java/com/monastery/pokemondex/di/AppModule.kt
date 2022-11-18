package com.monastery.pokemondex.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.monastery.pokemondex.api.ApiStoresInterface
import com.monastery.pokemondex.api.error.RxErrorHandlingCallAdapterFactory
import com.monastery.pokemondex.ext.logd
import com.monastery.pokemondex.utils.Constants
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideContext(application: Application): Context = application

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideApplicationInterceptor(): Interceptor {
        return Interceptor { chain ->
            val url = chain.request().url.newBuilder()
                .build()

            val request = chain.request().newBuilder().url(url).apply { }.build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        // Debug時にはログを出力
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideSharedPreference(context: Context): SharedPreferences =
        context.getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideRetrofit(
        applicationInterceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        // Timeout時間を20秒に変更
        val client = OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(180, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(applicationInterceptor)
        "body 2 $loggingInterceptor $applicationInterceptor".logd()
        return Retrofit.Builder()
            .baseUrl(Constants.BaseUrl)
            .client(client.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiStoresInterface = retrofit.create(ApiStoresInterface::class.java)
}
