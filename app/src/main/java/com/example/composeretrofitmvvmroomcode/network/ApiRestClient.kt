package com.example.composeretrofitmvvmroomcode.network

import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit


class ApiRestClient {
    private var retrofit: Retrofit? = null
    @Synchronized
    fun getClient(): Retrofit? {
        if (retrofit == null) {
            val okHttpClient = getOkHttpClient()
            retrofit = Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
        return retrofit
    }

    private fun getOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        return OkHttpClient.Builder()
            .readTimeout(90, TimeUnit.SECONDS)
            .certificatePinner(
                CertificatePinner.Builder()
                    /*.add(HOST_NAME, SHA_256_KEY_LATEST)*/
                    /*.add(HOST_NAME, SHA_256_KEY_OLD)*/
                    .build()
            )
          /*   .addInterceptor(
                 if (BuildConfig.DEBUG) httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY) else httpLoggingInterceptor.setLevel(
                     HttpLoggingInterceptor.Level.NONE
                 )
             )*/
            .connectTimeout(90, TimeUnit.SECONDS)
            .build()
    }
}