package com.android.cinema

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.android.cinema.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val movie = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_MOVIE, Movie::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_MOVIE)
        }

        if (movie != null) {
            showDetailMovie(movie)
        }

    }

    private fun showDetailMovie(movie: Movie) {
        supportActionBar?.title = movie.name
        Glide.with(binding.root.context)
            .load(movie.imageUrl)
            .into(binding.imgPoster)
        binding.tvTitle.text = movie.name
        binding.tvRating.text = "Rating: ${movie.rate}"
        binding.tvReleaseDate.text = "Release Date: ${movie.releaseDate}"
        binding.tvOverview.text = movie.overview
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
    }
}