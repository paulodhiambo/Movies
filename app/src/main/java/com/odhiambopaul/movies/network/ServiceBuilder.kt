package com.odhiambopaul.movies.network

import com.odhiambopaul.movies.util.BASE_URL
import com.odhiambopaul.movies.util.api_key
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    private val authInterceptor = Interceptor{chain ->
        val newurl= chain.request().url
            .newBuilder()
            .addQueryParameter("api_key", api_key)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newurl)
            .build()

        chain.proceed(newRequest)
    }
    private val client = OkHttpClient
        .Builder()
        .addInterceptor(authInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(TmdbEndpoints::class.java)

    fun buildService(): TmdbEndpoints {
        return retrofit
    }
}