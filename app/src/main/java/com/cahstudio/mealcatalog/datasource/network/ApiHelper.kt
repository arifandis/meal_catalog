package com.cahstudio.mealcatalog.datasource.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiHelper {
    companion object{
        fun getClient(): Retrofit {
            val client = OkHttpClient.Builder()
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            client.interceptors().add(interceptor)
            client.connectTimeout(20, TimeUnit.SECONDS)
            client.readTimeout(20, TimeUnit.SECONDS)
            client.writeTimeout(20, TimeUnit.SECONDS)

            val gson = GsonBuilder()
                .setLenient()
                .create()

            return Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client.build())
                .build()
        }
    }
}