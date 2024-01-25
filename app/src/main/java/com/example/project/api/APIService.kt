package com.example.project.api

import SessionManager
import android.util.Log
import com.example.project.interceptors.accessTokenInterceptor
import com.example.project.interceptors.refreshTokenInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object APIService {
    private const val BASE_URL="https://backendmobilegl4.onrender.com/api/"
    private val clientBuilder: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(60,TimeUnit.SECONDS)
        .readTimeout(60,TimeUnit.SECONDS)
        .writeTimeout(60,TimeUnit.SECONDS)
        .build()

    private val authedClientBuilder: OkHttpClient = clientBuilder.newBuilder()
        .addInterceptor { chain -> accessTokenInterceptor(chain) }
        .addInterceptor { chain -> refreshTokenInterceptor(chain) }
        .build()

    fun getAuthService(): AuthRoute {
        val client: OkHttpClient = clientBuilder.newBuilder()
            .build()

        val builder: Retrofit.Builder=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())

        val retrofit: Retrofit=builder.build()
        return retrofit.create(AuthRoute::class.java)
    }

    fun getPostService(): PostRoute {
        val client: OkHttpClient = authedClientBuilder.newBuilder()
            .build()

        val builder: Retrofit.Builder=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())

        val retrofit: Retrofit=builder.build()
        return retrofit.create(PostRoute::class.java)
    }

    fun getUserService(): UserRoute {
        val client: OkHttpClient = authedClientBuilder.newBuilder()
            .build()

        val builder: Retrofit.Builder=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())

        val retrofit: Retrofit=builder.build()
        return retrofit.create(UserRoute::class.java)
    }
}