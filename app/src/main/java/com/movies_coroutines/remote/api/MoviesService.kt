package com.movies_coroutines.remote.api

import com.movies_coroutines.remote.model.ResponseWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

//    @GET("movie/popular")
//    fun getPopularMovies(): Observable<ResponseWrapper>

    @GET("movie/popular")
    suspend fun getPopularMoviesPaging(@Query("page") page: Int): ResponseWrapper
}