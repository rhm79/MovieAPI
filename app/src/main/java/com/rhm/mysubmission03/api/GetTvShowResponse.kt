package com.rhm.mysubmission03.api

import com.google.gson.annotations.SerializedName

data class GetTvShowResponse(
    @SerializedName("results") val tvShows: List<TvShowItems>
)