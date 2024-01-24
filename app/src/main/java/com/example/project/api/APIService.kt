package com.example.project.api

import SessionManager
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
        val client: OkHttpClient = clientBuilder.newBuilder()
            .addInterceptor { chain: Interceptor.Chain ->
                val reqBuilder: Request.Builder = chain.request().newBuilder()
                if(SessionManager.isLoggedIn()){0
                    val token = SessionManager.getAccessToken()!!
                    reqBuilder.addHeader("Authorization", "Bearer $token")
                }
                val newReq = reqBuilder.build()
                chain.proceed(newReq)
            }
            .build()

        val builder: Retrofit.Builder=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())

        val retrofit: Retrofit=builder.build()

        return retrofit.create(PostRoute::class.java)
    }
}