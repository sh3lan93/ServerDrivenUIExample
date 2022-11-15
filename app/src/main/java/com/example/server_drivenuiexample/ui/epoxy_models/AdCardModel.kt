package com.example.server_drivenuiexample.ui.epoxy_models

import android.annotation.SuppressLint
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.example.server_drivenuiexample.R
import com.example.server_drivenuiexample.common.clicklisteners.ButtonClickListener
import com.example.server_drivenuiexample.ui.design_system_language.ActionTypes
import com.example.server_drivenuiexample.ui.design_system_language.ScaleType
import com.example.server_drivenuiexample.ui.design_system_language.Style

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.advertising_card)
abstract class AdCardModel : DataBindingEpoxyModel(), ClickableModel {
    @EpoxyAttribute
    lateinit var adImage: String

    @EpoxyAttribute
    lateinit var adImageRatio: String

    @EpoxyAttribute
    lateinit var overlayColor: String

    @EpoxyAttribute
    var overlayOpacity: Float = 1f

    @EpoxyAttribute
    lateinit var adText: String

    @EpoxyAttribute
    lateinit var adTextStyle: Style

    @EpoxyAttribute
    lateinit var adTextColor: String

    @EpoxyAttribute
    var adTextMargin: Int = 0

    @EpoxyAttribute
    lateinit var adButtonText: String

    @EpoxyAttribute
    var adButtonMargin: Int = 0

    @EpoxyAttribute
    var adButtonTextPadding: Int = 0

    @EpoxyAttribute
    lateinit var adButtonColor: String

    @EpoxyAttribute
    lateinit var adButtonTextColor: String

    @EpoxyAttribute
    var adCornerRadius: Int = 0

    @EpoxyAttribute
    lateinit var scaleType: ScaleType

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var clickListener: ButtonClickListener

    @EpoxyAttribute
    lateinit var actionType: ActionTypes

    @EpoxyAttribute
    lateinit var actionUri: String


    override fun initClickListener(clickListener: ButtonClickListener) {
        this.clickListener = clickListener
    }
}