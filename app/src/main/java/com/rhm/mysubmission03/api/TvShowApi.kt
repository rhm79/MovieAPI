package com.rhm.mysubmission03.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TvShowApi {

    @GET("discover/tv")
    fun getDiscoverTvShows(
        @Query("api_key") apiKey: String = "b9f348cb3669613861ca4cd300c88fd4",
        @Query("page") page: Int
    ): Call<GetTvShowResponse>
}