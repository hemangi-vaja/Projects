package com.aspl.technometricspracticaltask.respository

import com.aspl.technometricspracticaltask.ApiResponse
import com.aspl.technometricspracticaltask.RemoteDataSource
import com.aspl.technometricspracticaltask.model.MovieModel
import com.aspl.technometricspracticaltask.retrofit.BaseApiResponse
import com.aspl.technometricspracticaltask.retrofit.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class MovieRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    BaseApiResponse() {

    suspend fun getMovieList(): Flow<NetworkResult<ApiResponse<List<MovieModel>>>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getMovieList() })
        }.flowOn(Dispatchers.IO)
    }
}