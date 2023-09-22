package com.example.assignmnet_img.search.retrofit

import com.example.assignmnet_img.search.SearchFragment
import com.example.assignmnet_img.unit.Unit.API_KEY
import com.example.assignmnet_img.unit.Unit.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val api: RetrofitInterface get() = instance.create(RetrofitInterface::class.java)

    private val instance: Retrofit
        get() {
            val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
                val request: Request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", "KakaoAK ${API_KEY}")
                    .build()
                chain.proceed(request)
            }.build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()
        }
}