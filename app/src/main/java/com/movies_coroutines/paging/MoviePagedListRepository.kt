package com.movies_coroutines.paging

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.movies_coroutines.app.utils.POST_PER_PAGE
import com.movies_coroutines.remote.model.Movie
import com.movies_coroutines.source.RemoteDataSource
import kotlinx.coroutines.Dispatchers

class MoviePagedListRepository (private val remoteDataSource: RemoteDataSource) {

    lateinit var moviePagedList: LiveData<PagedList<Movie>>
    lateinit var moviesDataSourceFactory: MoviesDataSourceFactory

    fun fetchLiveMoviePagedList(): LiveData<PagedList<Movie>> {
        moviesDataSourceFactory = MoviesDataSourceFactory(remoteDataSource, Dispatchers.IO)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .setInitialLoadSizeHint(POST_PER_PAGE * 2)
            .setPrefetchDistance(1)
            .build()

        moviePagedList = LivePagedListBuilder(moviesDataSourceFactory, config).build()

        return moviePagedList
    }

//    fun getNetworkState(): LiveData<NetworkState> {
//        return Transformations.switchMap<MovieDataSource, NetworkState>(
//            moviesDataSourceFactory.moviesLiveDataSource, MovieDataSource::networkState)
//    }
}