package com.sawadikap.sawadikap.data.remote

import com.sawadikap.sawadikap.data.entity.Cloth
import com.sawadikap.sawadikap.domain.model.request.LoginRequest
import com.sawadikap.sawadikap.domain.model.request.SignUpRequest
import com.sawadikap.sawadikap.domain.model.response.LoginResponse
import com.sawadikap.sawadikap.domain.model.response.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SawadikapService {
    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("signup")
    fun signup(@Body signUpRequest: SignUpRequest): Call<SignUpResponse>

    @GET("pakaian/{id}")
    fun getUserClothes(@Path("id") id: Int): Call<List<Cloth>>
}
