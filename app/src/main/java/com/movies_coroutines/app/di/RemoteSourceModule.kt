package com.movies_coroutines.app.di

import com.movies_coroutines.BuildConfig
import com.movies_coroutines.BuildConfig.API_KEY
import com.movies_coroutines.remote.api.MoviesService
import com.movies_coroutines.source.RemoteDataSource
import com.movies_coroutines.source.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module(includes = [RemoteSourceModule.Binders::class])
class RemoteSourceModule {

    @Module
    interface Binders {
        @Binds
        fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
    }

    @Provides
    fun providesMoviesService(retrofit: Retrofit): MoviesService = retrofit.create(MoviesService::class.java)

    @Provides
    fun providesRetrofit(): Retrofit =
        Retrofit.Builder()
            .client(getOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()

    private fun getOkHttpClient(): OkHttpClient {
        val requestInterceptor = Interceptor { chain ->
            val url = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)
        }

        return OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }
}