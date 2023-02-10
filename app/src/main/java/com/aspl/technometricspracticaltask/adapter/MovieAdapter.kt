package com.aspl.technometricspracticaltask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aspl.technometricspracticaltask.databinding.ItemMoviesListBinding
import com.aspl.technometricspracticaltask.model.MovieModel

class MovieAdapter(private val context: Context, private var movieList: List<MovieModel>, private val listener: (MovieModel) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemMoviesListBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = ItemMoviesListBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movieList[position]
        holder.binding.ivImage.load(
            "https://image.tmdb.org/t/p/w500/" + item.poster_path
        )
        holder.binding.tvName.text=item.title
        holder.binding.tvDate.text=item.release_date
        holder.binding.tvRate.text=item.vote_average.toString() + " (" + item.vote_count + ")"
        holder.itemView.setOnClickListener {
            listener.invoke(item)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
    fun filterList(filterList: List<MovieModel>) {
        movieList = filterList
        notifyDataSetChanged()
    }
    fun getList(): List<MovieModel> {
        return movieList
    }
}