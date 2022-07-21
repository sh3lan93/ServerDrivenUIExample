package com.example.server_drivenuiexample.ui.utils

import android.graphics.Color
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.databinding.BindingAdapter
import coil.load
import com.example.server_drivenuiexample.R
import com.example.server_drivenuiexample.ui.design_system_language.Style


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

@BindingAdapter("app:image_url")
fun setImageRatio(view: ImageView, imageUrl: String) {
    view.load(data = imageUrl)
}

@BindingAdapter("app:ratio")
fun setRatio(view: ImageView, ratio: String) {
    val layoutParams = view.layoutParams as ConstraintLayout.LayoutParams
    layoutParams.dimensionRatio = ratio
    view.layoutParams = layoutParams
}

@BindingAdapter("app:text_color")
fun setTextColor(view: TextView, color: String) {
    view.setTextColor(Color.parseColor(color))
}

@BindingAdapter("app:opacity")
fun setTextColorOpacity(view: TextView, opacity: Float) {
    view.alpha = opacity
}

@BindingAdapter(
    value = ["app:margin_start", "app:margin_end", "app:margin_top", "app_margin_bottom"],
    requireAll = false
)
fun setViewMargin(view: View, start: Int = 0, end: Int = 0, top: Int = 0, bottom: Int = 0) {
    val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
    layoutParams.setMargins(start, top, end, bottom)
    view.layoutParams = layoutParams
}

@BindingAdapter("app:style")
fun setStyle(view: TextView, style: Style) {
    view.setTextAppearance(
        when (style) {
            Style.PRICE -> R.style.Price
            Style.HEADING_4 -> R.style.Header4
            Style.BODY -> R.style.Body
            Style.UNKNOWN -> 0
        }
    )
}
