package com.rhm.mysubmission03.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.rhm.mysubmission03.R
import com.rhm.mysubmission03.api.MovieItems
import com.rhm.mysubmission03.api.TvShowItems
import kotlinx.android.synthetic.main.activity_detail_click.*

class DetailClickActivity : AppCompatActivity() {

    private lateinit var verifyView: String //

    companion object {
        const val EXTRA_ADAPTER = "extra_adapter"
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_click)

        val tvTitle: TextView = findViewById(R.id.tv_title)
        val tvReleaseDate: TextView = findViewById(R.id.tv_date_release)
        val tvDescription: TextView = findViewById(R.id.tv_description)
        val ivPoster: ImageView = findViewById(R.id.iv_Poster)

        val fromAdapter = intent.getStringExtra(EXTRA_ADAPTER)

        if (fromAdapter == "movie") {

            verifyView = fromAdapter
            val movie = intent.getParcelableExtra(EXTRA_MOVIE) as MovieItems

            showLoading(true)

            tvTitle.text = movie.title
            tvReleaseDate.text = movie.releaseDate
            tvDescription.text = movie.overview

            val stringPoster: String = "https://image.tmdb.org/t/p/w342" + movie.posterPath

            Glide.with(this)
                .load(stringPoster)
                .into(ivPoster)

            if (tvDescription.text != ".") {
                showLoading(false)
            }

            supportActionBar!!.title = movie.title
        } else if (fromAdapter == "tvShow") {

            verifyView = fromAdapter
            val tvShow = intent.getParcelableExtra(EXTRA_TV_SHOW) as TvShowItems

            showLoading(true)

            tvTitle.text = tvShow.name
            tvReleaseDate.text = tvShow.firstAirDate
            tvDescription.text = tvShow.overview

            val stringPoster: String = "https://image.tmdb.org/t/p/w342" + tvShow.posterPath

            Glide.with(this)
                .load(stringPoster)
                .into(ivPoster)

            if (tvDescription.text != ".") {
                showLoading(false)
            }

            supportActionBar!!.title = tvShow.name
        }

        if (savedInstanceState != null) {  //
            //val result = savedInstanceState.getString(MainFragment.STATE_RESULT) as String  //
            val result = savedInstanceState.getString(EXTRA_ADAPTER) as String
            verifyView = result  //
        }  //

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(EXTRA_ADAPTER, verifyView)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBarDetail.visibility = View.VISIBLE
        } else {
            progressBarDetail.visibility = View.GONE
        }
    }

}
