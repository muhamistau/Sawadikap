package com.sawadikap.sawadikap.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SawadikapRemote {
    fun create(): SawadikapService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://sawadikap.himatif.org/api/")
            .build()
        return retrofit.create(SawadikapService::class.java)
    }
}