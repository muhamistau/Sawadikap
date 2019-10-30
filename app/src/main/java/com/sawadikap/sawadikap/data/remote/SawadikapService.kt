package com.sawadikap.sawadikap.data.remote

import com.sawadikap.sawadikap.data.entity.Cloth
import com.sawadikap.sawadikap.data.entity.Trophy
import com.sawadikap.sawadikap.domain.model.request.LoginRequest
import com.sawadikap.sawadikap.domain.model.request.SignUpRequest
import com.sawadikap.sawadikap.domain.model.request.TrophyRequest
import com.sawadikap.sawadikap.domain.model.response.LoginResponse
import com.sawadikap.sawadikap.domain.model.response.SignUpResponse
import com.sawadikap.sawadikap.domain.model.response.TrophyResponse
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

    @GET("banyakSedekah/{id}")
    fun getNumberRecord(@Path("id") id: Int): Call<Int>

    @GET("pakaian/{id}")
    fun getUserClothes(@Path("id") id: Int): Call<List<Cloth>>

    @GET("piala/{id}")
    fun getUserTrophy(@Path("id") id: Int): Call<List<Trophy>>

    @POST("piala")
    fun addToTrophyCabinet(@Body trophyRequest: TrophyRequest): Call<TrophyResponse>
}
