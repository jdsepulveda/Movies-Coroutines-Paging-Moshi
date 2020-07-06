package com.movies_coroutines.source

import com.movies_coroutines.remote.model.ResponseWrapper

interface RemoteDataSource {

//    fun getPopularMovies(): Observable<ResponseWrapper>

    suspend fun getPopularMoviesPaging(page: Int): ResponseWrapper
}