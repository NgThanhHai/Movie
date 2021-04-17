package com.example.moviekotlin.viewmodels

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.moviekotlin.models.Results
import com.example.moviekotlin.repositories.MovieRepository

class ListMovieViewModels : ViewModel() {

    lateinit var movieRepo: MovieRepository
    var isLoading = MutableLiveData<Boolean>(true)
    private var movieList = MutableLiveData<List<Results>>()
    var isError = MutableLiveData<Boolean>(false)
    var isEmpty = MutableLiveData<Boolean>(true)


    fun getMovieList(): LiveData<List<Results>>
    {
        return movieList
    }

    fun fetchMovie(api_key:String, language: String, page: Int) {

        movieRepo = MovieRepository.getInstance()

        isLoading.value = true

        movieRepo.getUpcomingMovies(api_key, language,  page){

            isSuccess, response ->

            isLoading.value = false

            if( isSuccess ){

                if( response!!.isEmpty() )    {

                    isEmpty.value = true

                }   else    {
                    isEmpty.value = false

                    movieList.value = response
                }
            }   else    {

                isError.value = true

                isEmpty.value = true
            }

        }

    }

}
