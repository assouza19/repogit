package com.br.repogit.data.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

private const val JSON_MEDIA_TYPE = "application/json"
private const val BASE_URL = "https://api.github.com"

object RetrofitClient {

    fun newInstance(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttp)
            .addConverterFactory(getKSerializationConverterFactory())
            .build()

    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }

    private fun getKSerializationConverterFactory(): Converter.Factory {
        val contentType = MediaType.get(JSON_MEDIA_TYPE)
        val json = buildJson()

        return json.asConverterFactory(contentType)
    }

    private fun buildJson() =
        Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
            isLenient = true
        }
}
