package com.szenasi.test.di.module

import android.app.Application
import android.content.Context
import com.squareup.moshi.Moshi
import com.szenasi.test.data.GetItemsApi
import com.szenasi.test.data.GetItemsDataSource
import com.szenasi.test.utils.BASE_URL
import com.szenasi.test.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideGetItemsDataSource(getItemsApi: GetItemsApi) = GetItemsDataSource(getItemsApi)

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()

    @Provides
    internal fun provideGetItemsApi(retrofit: Retrofit): GetItemsApi {
        return retrofit.create(GetItemsApi::class.java)
    }

    @Provides
    internal fun provideRetrofitInterface(schedulerProvider: SchedulerProvider, okHttpClient: OkHttpClient): Retrofit {
        val moshi = Moshi.Builder()
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(schedulerProvider.io()))
            .build()
    }

    @Provides
    internal fun provideOkHttp(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }


}