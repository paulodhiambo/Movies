package com.odhiambopaul.movies.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.odhiambopaul.movies.R
import com.odhiambopaul.movies.network.Result
import com.odhiambopaul.movies.ui.detail.MovieDetail

class MoviesAdapter(val movies: List<Result>, private val context: Context) :
    RecyclerView.Adapter<MoviesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            Toast.makeText(
                context,
                "Poster Url: ${movies[position].poster_path}",
                Toast.LENGTH_LONG
            ).show()
            val intent = Intent(context, MovieDetail::class.java).also {
                it.putExtra(
                    "poster",
                    "http://image.tmdb.org/t/p/w500${movies[position].poster_path}"
                )
                it.putExtra("title", movies[position].title)
                it.putExtra("release", movies[position].release_date)
                it.putExtra("overview", movies[position].overview)
            }
            context.startActivity(intent)
        }
        return holder.bind(movies[position])
    }
}

class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val photo: ImageView = itemView.findViewById(R.id.movie_photo)
    private val title: TextView = itemView.findViewById(R.id.movie_title)
    private val overview: TextView = itemView.findViewById(R.id.movie_overview)
    private val rating: TextView = itemView.findViewById(R.id.movie_rating)

    fun bind(movie: Result) {
        Glide.with(itemView.context).load("http://image.tmdb.org/t/p/w185${movie.poster_path}")
            .into(photo)
        title.text = "Title: " + movie.title
        overview.text = movie.overview
        rating.text = "Rating : " + movie.vote_average.toString()

    }

}
