package com.golfcourse.finderapp.network

import android.util.Log


import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {



    val instance: API by lazy {

        val client = OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).addInterceptor(AuthInterceptor())
            connectTimeout(160, TimeUnit.SECONDS)
            readTimeout(160, TimeUnit.SECONDS)
            writeTimeout(160, TimeUnit.SECONDS)
        }.build()


        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.golfcourseapi.com/v1/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        retrofit.create(API::class.java)
    }

    class AuthInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val requestBuilder = chain.request().newBuilder()

            requestBuilder.header("Content-Type", "application/json");
            requestBuilder.header("Accept", "application/json");

            // save api key and pass it in header

            // later i will add to Firebase or backend side for security purpose .


            val token = "GNCIJAZ4ILS2SNYAQ5LNQAK7Q4"
            Log.w("vishaltoken",""+token.toString())


            if (!token.isNullOrEmpty()) {
                requestBuilder.addHeader("Authorization", "Key "+token)
            }
            return chain.proceed(requestBuilder.build())
        }
    }

}