package com.movies_coroutines.paging

import androidx.paging.PageKeyedDataSource
import com.movies_coroutines.app.utils.FIRST_PAGE
import com.movies_coroutines.remote.model.Movie
import com.movies_coroutines.source.RemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MoviesDataSource(
    private val remoteDataSource: RemoteDataSource,
    coroutineContext: CoroutineContext
) : PageKeyedDataSource<Int, Movie>() {

    private var page = FIRST_PAGE
    private val job = Job()
    private val scope = CoroutineScope(coroutineContext + job)

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        scope.launch {
            try {
                callback.onResult(
                    remoteDataSource.getPopularMoviesPaging(page).results,
                    null,
                    page + 1
                )
            } catch (e: Throwable) { }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        scope.launch {
            try {
                callback.onResult(
                    remoteDataSource.getPopularMoviesPaging(params.key).results,
                    params.key + 1
                )
            } catch (e: Throwable) { }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) { }
}