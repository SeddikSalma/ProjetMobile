package com.example.project.interceptors

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

fun accessTokenInterceptor(chain: Interceptor.Chain): Response {
    val reqBuilder: Request.Builder = chain.request().newBuilder()
    if(SessionManager.isLoggedIn()){
        val token = SessionManager.getAccessToken()!!
        reqBuilder.addHeader("Authorization", "Bearer $token")
    }
    val newReq = reqBuilder.build()

    return chain.proceed(newReq)
}