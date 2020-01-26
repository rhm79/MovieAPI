package com.rhm.mysubmission03.repo

import android.util.Log
import com.rhm.mysubmission03.api.GetMovieResponse
import com.rhm.mysubmission03.api.MovieApi
import com.rhm.mysubmission03.api.MovieItems
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRepository {

    private val apiMovie: MovieApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiMovie = retrofit.create(
            MovieApi::class.java
        )
    }

    fun getDiscoverMovies(
        page: Int = 1,
        onSuccess: (movie: List<MovieItems>) -> Unit,
        onError: () -> Unit
    ) {
        apiMovie.getDiscoverMovies(page = page)
            .enqueue(object : Callback<GetMovieResponse> {
                override fun onResponse(
                    call: Call<GetMovieResponse>,
                    response: Response<GetMovieResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.movies)
                        } else {
                            onError.invoke()
                        }
                    }
                }

                override fun onFailure(call: Call<GetMovieResponse>, t: Throwable) {
                    Log.e("Repository", "onFailure", t)
                }
            })
    }

}