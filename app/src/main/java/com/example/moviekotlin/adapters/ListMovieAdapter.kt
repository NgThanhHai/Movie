package com.example.moviekotlin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviekotlin.R
import com.example.moviekotlin.databinding.LayoutMovieItemBinding
import com.example.moviekotlin.models.Results


class ListMovieAdapter( private val movies: List<Results>) : RecyclerView.Adapter<ListMovieAdapter.ViewHolder>()    {

    override fun getItemCount(): Int {

        return movies.size

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :ViewHolder {

        val layoutMovieItemBinding : LayoutMovieItemBinding = DataBindingUtil.inflate(

            LayoutInflater.from(parent.context),R.layout.layout_movie_item,parent,false

        )

        return ViewHolder(layoutMovieItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.layoutMovieItemBinding.movie = movies[position]

    }


    class ViewHolder(   itemView: LayoutMovieItemBinding    ) : RecyclerView.ViewHolder(itemView.root) {

        var layoutMovieItemBinding :LayoutMovieItemBinding = itemView

    }

}

