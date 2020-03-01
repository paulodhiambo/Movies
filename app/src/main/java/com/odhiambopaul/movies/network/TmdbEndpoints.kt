package com.odhiambopaul.movies.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbEndpoints {

//    @GET("/3/movie/popular")
//    fun getMovies(@Query("api_key") key: String): Observable<PopularMovies>

    @GET("/3/movie/popular")
    suspend fun getMovies(@Query("api_key") key: String): Response<PopularMovies>

}