package com.example.android.heysports.util

import android.view.View
import androidx.databinding.BindingAdapter
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
