package com.example.aeontask

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<TokenResponse>

    @GET("/payments")
    suspend fun getPayments(@Header("token") token: String): Response<List<Payment>>
}

data class LoginRequest(val login: String, val password: String)
data class TokenResponse(val token: String)
data class Payment(val id: String, val amount: Double, val description: String)
