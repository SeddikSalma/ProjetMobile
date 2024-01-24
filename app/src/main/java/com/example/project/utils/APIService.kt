package com.example.project.utils

import SessionManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object APIService {
    private const val BASE_URL="https://backendmobilegl4.onrender.com/api/"
    fun getService():APIConsumer{
        val client: OkHttpClient=OkHttpClient.Builder()
            .connectTimeout(60,TimeUnit.SECONDS)
            .readTimeout(60,TimeUnit.SECONDS)
            .writeTimeout(60,TimeUnit.SECONDS)
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

        return retrofit.create(APIConsumer::class.java)
    }
}