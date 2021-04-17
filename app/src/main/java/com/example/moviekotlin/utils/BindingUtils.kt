package com.example.moviekotlin.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image")
fun loadImage(image: ImageView, poster_path: String){

    val url: String = "http://image.tmdb.org/t/p/w220_and_h330_face" + poster_path.subSequence(0,poster_path.length)

    Glide.with(image).load(url).into(image)

}