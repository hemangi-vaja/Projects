package com.aspl.technometricspracticaltask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.aspl.technometricspracticaltask.databinding.ActivityMainBinding
import com.aspl.technometricspracticaltask.databinding.ActivityMovieDetailsBinding
import com.aspl.technometricspracticaltask.model.MovieModel

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val movieModel = intent.getParcelableExtra<MovieModel>("movie")
        binding.ivBack.setOnClickListener {
            finish()
        }
        if (movieModel != null) {
            binding.ivImage.load("https://image.tmdb.org/t/p/w500/" + movieModel.poster_path)
            binding.tvMovieName.text = movieModel.title
            binding.tvOverview.text = movieModel.overview
            binding.tvReleaseDate.text = movieModel.release_date
            binding.tvRating.text = movieModel.vote_average.toString() + " (" + movieModel.vote_count + ")"
        }

    }
}