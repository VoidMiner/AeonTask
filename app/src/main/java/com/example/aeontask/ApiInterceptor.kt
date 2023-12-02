package com.example.aeontask

import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // Добавление заголовков
        val modifiedRequest = originalRequest.newBuilder()
            .header("app-key", "12345")
            .header("v", "1")
            .build()

        return chain.proceed(modifiedRequest)
    }
}
