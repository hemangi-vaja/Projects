package com.aspl.technometricspracticaltask.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.aspl.technometricspracticaltask.ApiResponse
import com.aspl.technometricspracticaltask.model.MovieModel
import com.aspl.technometricspracticaltask.respository.MovieRepository
import com.aspl.technometricspracticaltask.retrofit.NetworkResult
import com.aspl.technometricspracticaltask.room.AppDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    application: Application
) : AndroidViewModel(application) {

    private val context = application.applicationContext

//    init {
//        getMovieList()
//    }

    private val _response: MutableLiveData<NetworkResult<ApiResponse<List<MovieModel>>>> =
        MutableLiveData()

    val response: LiveData<NetworkResult<ApiResponse<List<MovieModel>>>> = _response

    fun getMovieList() = viewModelScope.launch {
        movieRepository.getMovieList().collect { values ->
            when (values) {
                is NetworkResult.Success -> {
                    Log.d("<>", "getMovieList: " + values.data)
                    for (movie in values.data!!.results) {
                        AppDatabase.getDatabase(context).movieDao().insertMovie(movie)
                    }
                }
                is NetworkResult.Error -> {

                }
                is NetworkResult.Loading -> {

                }
            }
        }
    }
}