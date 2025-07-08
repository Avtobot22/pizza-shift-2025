package com.example.pizza_shift_intensive.di

import com.example.pizza_shift_intensive.data.api.PizzaApi
import com.example.pizza_shift_intensive.data.converter.PizzaCatalogConverter
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://shift-intensive.ru/api/pizza/"
private const val CONNECT_TIMEOUT = 10L
private const val WRITE_TIMEOUT = 10L
private const val READ_TIMEOUT = 10L

val NetworkModule = module {
    single {
        Json.asConverterFactory("application/json; charset=UTF8".toMediaType())
    }

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder().apply {
            getAll<Interceptor>().forEach {
                addInterceptor(it)
            }
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        }.build()
    }

    single {
        Retrofit.Builder().apply {
            client(get())
            baseUrl(BASE_URL)
            addConverterFactory(get())
        }.build()
    }

}