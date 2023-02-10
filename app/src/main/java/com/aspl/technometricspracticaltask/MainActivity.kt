package com.aspl.technometricspracticaltask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.aspl.technometricspracticaltask.adapter.MovieAdapter
import com.aspl.technometricspracticaltask.databinding.ActivityMainBinding
import com.aspl.technometricspracticaltask.model.MovieModel
import com.aspl.technometricspracticaltask.retrofit.NetworkResult
import com.aspl.technometricspracticaltask.room.AppDatabase
import com.aspl.technometricspracticaltask.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import java.util.ArrayList

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var movieAdapter: MovieAdapter
    private var movieList = ArrayList<MovieModel>()
    lateinit var swipeRefreshListener: SwipeRefreshLayout.OnRefreshListener

    private val viewModel by viewModels<MovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        swipeRefreshListener = SwipeRefreshLayout.OnRefreshListener {
            Handler(Looper.getMainLooper()).postDelayed({
                binding.swipe.isRefreshing = false
                getMovieList()

            }, 3000)
        }

        viewModel.getMovieList()
        initRecyclerView()
        getMovieList()

        binding.swipe.setOnRefreshListener(swipeRefreshListener)
        binding.tvDate.setOnClickListener {
            binding.tvDate.setTextColor(getColor(R.color.purple_700))
            binding.tvRate.setTextColor(getColor(R.color.black))
            movieAdapter.filterList(movieAdapter.getList().sortedByDescending { it.release_date })

        }
        binding.tvRate.setOnClickListener {
            binding.tvRate.setTextColor(getColor(R.color.purple_700))
            binding.tvDate.setTextColor(getColor(R.color.black))
            movieAdapter.filterList(movieAdapter.getList().sortedByDescending { it.vote_average })
        }

    }

    private fun getMovieList() {
        runOnUiThread {
            val data = AppDatabase.getDatabase(applicationContext).movieDao().getMovieList()
            if (data.isEmpty()) {
                Log.d("TAG", "getMovieList: ")
                binding.swipe.isRefreshing = true
                swipeRefreshListener.onRefresh()
//                viewModel.getMovieList()
            } else {
                Log.d("TAG", "getMovieList: Data: ${data.size}")
                movieList.addAll(data)
                Log.d("TAG", "getMovieList: Movie list: ${movieList.size}")
                movieAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun initRecyclerView() {
        movieAdapter = MovieAdapter(this, movieList) { data ->
            val intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra("movie", data)
            startActivity(intent)
        }
        binding.rvMovies.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvMovies.adapter = movieAdapter
    }
}