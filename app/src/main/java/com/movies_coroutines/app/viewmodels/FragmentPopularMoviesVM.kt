package com.movies_coroutines.app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.movies_coroutines.paging.MoviePagedListRepository
import com.movies_coroutines.remote.model.Movie
import com.movies_coroutines.source.RemoteDataSource
import javax.inject.Inject

class FragmentPopularMoviesVM @Inject constructor(
    remoteDataSource: RemoteDataSource
) : ViewModel() {

    private val movieRepository = MoviePagedListRepository(remoteDataSource)

    val popularMoviesList : LiveData<PagedList<Movie>> by lazy {
        movieRepository.fetchLiveMoviePagedList()
    }
}