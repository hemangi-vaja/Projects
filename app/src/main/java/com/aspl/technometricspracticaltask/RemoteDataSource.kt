package com.aspl.technometricspracticaltask

import com.aspl.technometricspracticaltask.retrofit.RetrofitService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val retrofitService: RetrofitService) {


    suspend fun getMovieList() = retrofitService.getMovieList()


}