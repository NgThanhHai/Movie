package com.example.moviekotlin

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviekotlin.adapters.ListMovieAdapter
import com.example.moviekotlin.databinding.ActivityMainBinding
import com.example.moviekotlin.models.Results
import com.example.moviekotlin.viewmodels.ListMovieViewModels


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var listMovieViewModels: ListMovieViewModels

    private lateinit var listMovieAdapter: ListMovieAdapter

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        listMovieViewModels = ViewModelProvider(this).get(ListMovieViewModels::class.java)

        listMovieViewModels.fetchMovie("ffafbd7ad7beaef52e88f04b016c6a61", "en_US", 1)

        listMovieViewModels.getMovieList().observe(this, Observer {

            it?.let {

                initRecyclerView(it)

                listMovieAdapter.notifyDataSetChanged()

            }

        })

    }

    private fun initRecyclerView(it: List<Results>) {

        listMovieAdapter = ListMovieAdapter(it)

        mainBinding.listMovie.layoutManager = LinearLayoutManager(

            applicationContext,

            LinearLayoutManager.VERTICAL,

            false
        )

        var itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        itemDecoration.setDrawable(getDrawable(R.drawable.divider)!!)

        mainBinding.listMovie.addItemDecoration(    itemDecoration  )

        mainBinding.listMovie.adapter = listMovieAdapter

    }

}
