package com.ester.esterTrinityApp.di.modules

import com.ester.esterTrinityApp.data.source.Dao
import com.ester.esterTrinityApp.data.source.ApiService
import com.ester.esterTrinityApp.domain.datarepository.DataRepository
import com.ester.esterTrinityApp.data.TrinityRepositoryImplem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val NetworkModule = module {
    single { createService(get()) }
    single { createOkHttpClient() }
    single { createRetrofit(get(), "https://api.nasa.gov/mars-photos/") }

}

fun createTrinityRepository(trinityDao: Dao, nasaService: ApiService): DataRepository {
    return TrinityRepositoryImplem(trinityDao, nasaService)
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

fun createService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}
