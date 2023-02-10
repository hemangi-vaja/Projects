package com.aspl.technometricspracticaltask.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aspl.technometricspracticaltask.model.MovieModel

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movieModel: MovieModel)

    @Query("SELECT * FROM movies")
    fun getMovieList():  List<MovieModel>

}