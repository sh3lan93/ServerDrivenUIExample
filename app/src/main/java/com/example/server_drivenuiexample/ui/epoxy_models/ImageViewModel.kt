package com.example.server_drivenuiexample.ui.epoxy_models

import android.annotation.SuppressLint
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.example.server_drivenuiexample.R
import com.example.server_drivenuiexample.ui.design_system_language.ScaleType

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.imageview)
abstract class ImageViewModel : DataBindingEpoxyModel() {

    @EpoxyAttribute
    lateinit var ratio: String
    @EpoxyAttribute
    lateinit var imageUrl: String
    @EpoxyAttribute
    lateinit var scaleType: ScaleType
}