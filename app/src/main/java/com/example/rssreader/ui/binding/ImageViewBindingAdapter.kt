package com.example.rssreader.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.rssreader.R
import com.example.rssreader.model.GlideApp

object ImageViewBindingAdapter {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun setImageUrl(view: ImageView, url: String?) {
        GlideApp.with(view.context)
            .load(url)
            .placeholder(R.color.place_holder)
            .error(R.color.black)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}