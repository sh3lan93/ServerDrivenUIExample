package com.example.server_drivenuiexample.ui.models


import com.airbnb.epoxy.EpoxyModel
import com.example.server_drivenuiexample.ui.design_system_language.ComponentsType
import com.example.server_drivenuiexample.ui.design_system_language.Style
import com.example.server_drivenuiexample.ui.epoxy_models.AdCardModel_
import com.example.server_drivenuiexample.ui.epoxy_models.ImageViewModel_
import com.example.server_drivenuiexample.ui.utils.mapToComponentType
import com.example.server_drivenuiexample.ui.utils.mapToStyle
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Component(
    @Json(name = "actions")
    val actions: Actions?,
    @Json(name = "content")
    val content: Content?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "properties")
    val properties: Properties?,
    @Json(name = "type")
    val type: String?,
    @Json(name = "span_count")
    val spanCount: Int? = 1,
    @Json(name = "style")
    val style: String?
) {

    val componentType: ComponentsType
        get() = type?.mapToComponentType() ?: ComponentsType.UNKNOWN

    val componentStyle: Style
        get() = style?.mapToStyle() ?: Style.UNKNOWN

    fun toUI(): EpoxyModel<*> {
        return when (componentType) {
            ComponentsType.IMAGE_VIEW -> ImageViewModel_().id(id ?: "")
                .imageUrl(content?.url ?: "")
                .ratio(properties?.ratio ?: "")
                .scaleType(properties?.imageScaleType)
            ComponentsType.CARD -> {
                val model = AdCardModel_().id(id ?: "")
                content?.stack?.find { it.componentType == ComponentsType.IMAGE_VIEW }?.let {
                    model.adImage(it.content?.url ?: "")
                    model.adImageRatio(it.properties?.ratio ?: "")
                    model.scaleType(it.properties?.imageScaleType)
                }
                content?.stack?.find { it.componentType == ComponentsType.OVERLAY }?.let {
                    model.overlayColor(it.properties?.color ?: "")
                    model.overlayOpacity(it.properties?.opacity ?: 1F)
                }
                content?.stack?.find { it.componentType == ComponentsType.TEXT_VIEW }?.let {
                    model.adText(it.content?.text ?: "")
                    model.adTextStyle(it.componentStyle)
                    model.adTextColor(it.properties?.textColor)
                    model.adTextMargin(it.properties?.marginStart ?: 0)
                }
                content?.stack?.find { it.componentType == ComponentsType.TEXT_BUTTON }?.let {
                    model.adButtonText(it.content?.text)
                    model.adButtonMargin(it.properties?.marginEnd ?: 0)
                    model.adButtonTextPadding(it.properties?.padding ?: 0)
                    model.adButtonColor(it.properties?.color)
                    model.adButtonTextColor(it.properties?.textColor)
                    model.actionType(it.actions?.onClick?.clickType)
                    model.actionUri(it.actions?.onClick?.data?.url)
                }
                model.adCornerRadius(properties?.cornerRadius ?: 0)
                model
            }
            else -> TODO()
        }
    }
}