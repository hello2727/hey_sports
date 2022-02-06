package com.example.android.heysports.util.extension

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider

/**
 * Created by Jihye Noh
 * Date: 2022-02-07
 */
fun View.clipCornerToRound(radius: Float) {
    this.outlineProvider = object : ViewOutlineProvider() {
        override fun getOutline(view: View, outline: Outline) {
            outline.setRoundRect(0, 0, view.width, view.height, radius)
        }
    }
}