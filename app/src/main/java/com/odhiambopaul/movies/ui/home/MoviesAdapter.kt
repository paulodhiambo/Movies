package com.odhiambopaul.movies.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.odhiambopaul.movies.R
import com.odhiambopaul.movies.network.Result

class MoviesAdapter(val movies: List<Result>): RecyclerView.Adapter<MoviesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        return holder.bind(movies[position])
    }
}

class MoviesViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
    private val photo:ImageView = itemView.findViewById(R.id.movie_photo)
    private val title:TextView = itemView.findViewById(R.id.movie_title)
    private val overview:TextView = itemView.findViewById(R.id.movie_overview)
    private val rating:TextView = itemView.findViewById(R.id.movie_rating)

    fun bind(movie: Result) {
        Glide.with(itemView.context).load("http://image.tmdb.org/t/p/w500${movie.poster_path}").into(photo)
        title.text = "Title: "+movie.title
        overview.text = movie.overview
        rating.text = "Rating : "+movie.vote_average.toString()
    }

}
