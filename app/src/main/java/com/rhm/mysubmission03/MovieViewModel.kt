package com.rhm.mysubmission03

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rhm.mysubmission03.api.MovieItems
import com.rhm.mysubmission03.repo.MovieRepository

class MovieViewModel : ViewModel() {


    val movieRepo = MovieRepository
    val listMovie = MutableLiveData<ArrayList<MovieItems>>()

}