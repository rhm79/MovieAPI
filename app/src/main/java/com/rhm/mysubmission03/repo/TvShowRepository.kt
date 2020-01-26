package com.rhm.mysubmission03.repo

import android.util.Log
import com.rhm.mysubmission03.api.GetTvShowResponse
import com.rhm.mysubmission03.api.TvShowApi
import com.rhm.mysubmission03.api.TvShowItems
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TvShowRepository {

    private val apiTvShow: TvShowApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiTvShow = retrofit.create(
            TvShowApi::class.java
        )
    }

    fun getDiscoverTvShows(
        page: Int = 1,
        onSuccess: (tvShow: List<TvShowItems>) -> Unit,
        onError: () -> Unit
    ) {
        apiTvShow.getDiscoverTvShows(page = page)
            .enqueue(object : Callback<GetTvShowResponse> {
                override fun onResponse(
                    call: Call<GetTvShowResponse>,
                    response: Response<GetTvShowResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.tvShows)
                        } else {
                            onError.invoke()
                        }
                    }
                }

                override fun onFailure(call: Call<GetTvShowResponse>, t: Throwable) {
                    Log.e("Repository", "onFailure", t)
                }
            })
    }

}