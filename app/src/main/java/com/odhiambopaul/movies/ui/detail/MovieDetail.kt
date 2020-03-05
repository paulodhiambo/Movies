package com.odhiambopaul.movies.ui.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.odhiambopaul.movies.R

class MovieDetail : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val posterurl=intent.getStringExtra("poster")
        val titlestring=intent.getStringExtra("title")
        val releasestring = intent.getStringExtra("release")
        val overviewtext =intent.getStringExtra("overview")

        val image:ImageView=findViewById(R.id.poster)
        val title:TextView=findViewById(R.id.title)
        val release:TextView=findViewById(R.id.release)
        val overview:TextView=findViewById(R.id.overview)

        Glide.with(this).load(posterurl).into(image)
        title.text= "Title: $titlestring"
        release.text= "Release date: $releasestring"
        overview.text=overviewtext
    }
}
