package com.movies_coroutines.source

import com.movies_coroutines.remote.api.MoviesService
import com.movies_coroutines.remote.model.ResponseWrapper
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val moviesService: MoviesService
) : RemoteDataSource {

//    override fun getPopularMovies(): Observable<ResponseWrapper> {
//        return moviesService.getPopularMovies()
//    }

    override suspend fun getPopularMoviesPaging(page: Int): ResponseWrapper {
        return moviesService.getPopularMoviesPaging(page)
    }
}