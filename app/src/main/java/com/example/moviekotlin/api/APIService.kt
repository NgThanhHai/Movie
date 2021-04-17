package com.example.moviekotlin.api


import com.example.moviekotlin.models.Data
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("3/movie/upcoming")

    fun getUpComingMovies(@Query( "api_key") api_key:String, @Query("Language") language:String, @Query("page") page: Int) : retrofit2.Call<Data>

}