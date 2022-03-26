package com.example.android.heysports.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.android.heysports.R
import com.example.android.heysports.util.extension.clipCornerToRound

/**
 * Created by Jihye Noh
 * Date: 2022-02-07
 */
@BindingAdapter("clipCornerToRound")
fun clipCornerToRound(layout: View, radius: Float) {
    layout.clipToOutline = true
    layout.clipCornerToRound(radius)
}

@BindingAdapter("setImageUri")
fun ImageView.setImageUri(uri: String?) {
    Glide.with(context)
        .load(uri)
        .placeholder(R.drawable.ic_launcher_background)
        .error(R.drawable.ic_launcher_background)
        .into(this)
}
