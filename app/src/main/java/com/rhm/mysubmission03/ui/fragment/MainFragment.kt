package com.rhm.mysubmission03.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rhm.mysubmission03.R
import com.rhm.mysubmission03.adapter.MovieAdapter
import com.rhm.mysubmission03.adapter.TvShowAdapter
import com.rhm.mysubmission03.api.MovieItems
import com.rhm.mysubmission03.api.TvShowItems
import com.rhm.mysubmission03.repo.MovieRepository
import com.rhm.mysubmission03.repo.TvShowRepository
import kotlinx.android.synthetic.main.fragment_main.*


/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    private lateinit var discoverMoviesAdapter: MovieAdapter
    private lateinit var discoverTvShowsAdapter: TvShowAdapter

    private lateinit var verifyView: String //

    companion object {
        private const val STATE_RESULT = "state_result"  //new

        private val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(index: Int): MainFragment {
            val fragment = MainFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_SECTION_NUMBER, index)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var index = 1
        if (arguments != null) {
            index = arguments?.getInt(ARG_SECTION_NUMBER, 0) as Int
        }

        val verify = "$index"
        if (verify == "1") {
            verifyView = verify  //

            rv_movie_tvShow.layoutManager = LinearLayoutManager(this.activity)

            discoverMoviesAdapter = MovieAdapter(listOf())
            rv_movie_tvShow.adapter = discoverMoviesAdapter

            showLoading(true)

            MovieRepository.getDiscoverMovies(
                onSuccess = ::onDiscoverMoviesFetched,
                onError = ::onError
            )

        } else if (verify == "2") {
            verifyView = verify  //

            rv_movie_tvShow.layoutManager = LinearLayoutManager(this.activity)

            discoverTvShowsAdapter = TvShowAdapter(listOf())
            rv_movie_tvShow.adapter = discoverTvShowsAdapter

            showLoading(true)

            TvShowRepository.getDiscoverTvShows(
                onSuccess = ::onDiscoverTvShowsFetched,
                onError = ::onError
            )

        }

        if (savedInstanceState != null) {  //
            val result = savedInstanceState.getString(STATE_RESULT) as String  //
            verifyView = result  //
        }  //


    }

    //override fun onSaveInstanceState(outState: Bundle) {
    //    super.onSaveInstanceState(outState)
    //    outState.putString(STATE_RESULT, verifyView)
    //}

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(STATE_RESULT, verifyView)
        super.onSaveInstanceState(outState)
    }

    private fun onDiscoverMoviesFetched(movies: List<MovieItems>) {
        discoverMoviesAdapter.updateMovies(movies)
        showLoading(false)
    }

    private fun onDiscoverTvShowsFetched(tvShows: List<TvShowItems>) {
        discoverTvShowsAdapter.updateTvShows(tvShows)
        showLoading(false)
    }

    private fun onError() {
        Toast.makeText(this.activity, getString(R.string.error_fetch), Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }


}
