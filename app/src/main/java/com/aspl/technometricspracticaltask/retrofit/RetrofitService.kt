package com.aspl.technometricspracticaltask.retrofit

import com.aspl.technometricspracticaltask.ApiResponse
import com.aspl.technometricspracticaltask.helper.Const
import com.aspl.technometricspracticaltask.model.MovieModel
import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.*


interface RetrofitService {

    @GET(Const.MOVIE_LIST)
    suspend fun getMovieList():Response<ApiResponse<List<MovieModel>>>
}