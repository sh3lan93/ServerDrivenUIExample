package com.example.server_drivenuiexample.ui.utils

import android.graphics.Color
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.updateLayoutParams
import androidx.databinding.BindingAdapter


@BindingAdapter("app:height")
fun setViewHeight(view: View, height: Float) {
    val heightInDP = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        height,
        view.context.resources.displayMetrics
    )
    view.updateLayoutParams {
        this.height = heightInDP.toInt()
    }
}

@BindingAdapter("app:buttonBackground")
fun setButtonBackground(view: Button, backgroundColor: String) {
    view.setBackgroundColor(Color.parseColor(backgroundColor))
}

@BindingAdapter("app:buttonTextColor")
fun setButtonTextColor(view: Button, textColor: String) {
    view.setTextColor(Color.parseColor(textColor))
}

@BindingAdapter("app:marginStart")
fun setMarginStart(view: View, margin: Int) {
    val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
    layoutParams.marginStart = margin
    view.layoutParams = layoutParams
}

@BindingAdapter("app:marginEnd")
fun setMarginEnd(view: View, margin: Int) {
    val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
    layoutParams.marginEnd = margin
    view.layoutParams = layoutParams
}