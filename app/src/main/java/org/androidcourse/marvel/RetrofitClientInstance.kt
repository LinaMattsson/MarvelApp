package org.androidcourse.marvel

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {
    val MARVEL_API_BASE_URL: String = "https://gateway.marvel.com:443/v1/public/"

    val service: MarvelService = Retrofit.Builder()
        .baseUrl(MARVEL_API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(getOkHttpClient())
        .build()
        .create(MarvelService::class.java)
}

private fun getOkHttpClient(): OkHttpClient {

    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    val builder = OkHttpClient.Builder()
    builder.addInterceptor(addKeyAndHash())
    builder.addInterceptor(logging)
    val okHttpClient = builder.build()
    return okHttpClient
}

fun addKeyAndHash(): Interceptor = Interceptor { chain ->
    val params = mapOf("apikey" to "431f0534d43050f6bbda02e6e0f4652f", "hash" to "427b7fe0981f44532988ed38227bd2ee", "ts" to "1")

    val originalRequest = chain.request()
    val urlWithParams = originalRequest.url().newBuilder()
        .apply{ params.forEach { addQueryParameter(it.key, it.value) }}
        .build()
    val newRequest = originalRequest.newBuilder().url(urlWithParams).build()
    chain.proceed(newRequest)
}