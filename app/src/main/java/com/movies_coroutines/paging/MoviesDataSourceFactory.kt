package com.movies_coroutines.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.movies_coroutines.remote.model.Movie
import com.movies_coroutines.source.RemoteDataSource
import kotlin.coroutines.CoroutineContext

class MoviesDataSourceFactory(
    private val remoteDataSource: RemoteDataSource,
    private val coroutineContext: CoroutineContext
): DataSource.Factory<Int, Movie>() {

    private val moviesDataSourceLiveData = MutableLiveData<MoviesDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val moviesDataSource = MoviesDataSource(remoteDataSource, coroutineContext)

        moviesDataSourceLiveData.postValue(moviesDataSource)
        return moviesDataSource
    }
}
