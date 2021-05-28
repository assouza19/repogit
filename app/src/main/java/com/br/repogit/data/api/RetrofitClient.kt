package com.br.repogit.data.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.github.com"

class RetrofitClient {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private val interceptor = Interceptor { chain ->
        val builder = chain.request().newBuilder()

        chain.proceed(builder.build())
    }

    fun newInstance(): GitHubService = retrofit.create(GitHubService::class.java)

    private val gson: Gson by lazy { GsonBuilder().excludeFieldsWithoutExposeAnnotation().create() }

    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
}
