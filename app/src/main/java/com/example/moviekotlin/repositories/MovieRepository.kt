package com.example.moviekotlin.repositories

import android.util.Log
import com.example.moviekotlin.api.APIClient
import com.example.moviekotlin.models.Data
import com.example.moviekotlin.models.Results
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {

    companion object {

        private var instance: MovieRepository? = null

        fun getInstance() = instance ?: MovieRepository().also {

            instance = it

        }
    }

    fun getUpcomingMovies(api_key: String, language: String, page: Int, onResult: (isSuccess: Boolean, response: List<Results>?) -> Unit ) {

        APIClient.instance.getUpComingMovies(api_key, language, page)

            .enqueue(object : Callback<Data> {

                override fun onResponse(call: Call<Data>, response: Response<Data>) {

                    if (response.isSuccessful) {

                        onResult(true, response.body()?.results)

                    } else {

                        Log.d("Repo", "Khong co du lieu")
                    }
                }

                override fun onFailure(call: Call<Data>, t: Throwable) {

                    Log.d("Repo", t.message.toString())

                    onResult(false, null)

                }

            })

    }

}