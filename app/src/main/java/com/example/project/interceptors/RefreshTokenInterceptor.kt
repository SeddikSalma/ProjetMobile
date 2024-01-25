package com.example.project.interceptors

import com.example.project.api.APIService
import okhttp3.Interceptor
import okhttp3.Response

fun refreshTokenInterceptor(chain: Interceptor.Chain): Response {
    val mainReq = chain.request()
    var mainResp = chain.proceed(mainReq)
    mainResp.

    if(mainResp.code == 401 || mainResp.code == 403){
        val refresh = SessionManager.getRefreshToken()
        APIService.getAuthService()
            .refreshToken("Bearer $refresh")
            .execute().let { resp ->
                if(resp.isSuccessful) {
                    val newSession = resp.body()!!.data!!
                    SessionManager.saveAuthSession(newSession)

                    val newReq = mainReq.newBuilder()
                        .header("Authorization", "Bearer ${newSession.accessToken}")
                        .method(mainReq.method, mainReq.body)
                        .build()

                    mainResp = chain.proceed(newReq)
                }
            }
    }
    return mainResp
}