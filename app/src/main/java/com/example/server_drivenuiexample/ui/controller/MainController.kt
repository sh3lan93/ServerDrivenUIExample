package com.example.server_drivenuiexample.ui.controller

import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.carousel
import com.example.server_drivenuiexample.*
import com.example.server_drivenuiexample.common.clicklisteners.ButtonClickListener
import com.example.server_drivenuiexample.states.Result
import com.example.server_drivenuiexample.states.ScreenState
import com.example.server_drivenuiexample.ui.design_system_language.ActionTypes
import com.example.server_drivenuiexample.ui.design_system_language.ComponentsType
import com.example.server_drivenuiexample.ui.epoxy_models.ClickableModel
import com.example.server_drivenuiexample.ui.main.epoxy.EpoxyViewsIds
import com.example.server_drivenuiexample.ui.models.Component
import com.example.server_drivenuiexample.ui.utils.ComponentClickListener

class MainController(
    private val buttonClickListener: ComponentClickListener
) :
    EpoxyController(
    ), ButtonClickListener {

    var screenContent: Result<List<Component>>? = null
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        screenContent?.let { result ->
            when (result.whichState()) {
                ScreenState.LOADING -> buildLoadingState()
                ScreenState.SUCCESS -> buildSuccessState(result.fetchData() ?: emptyList())
                ScreenState.ERROR -> buildErrorState()
                ScreenState.Uninitialized -> {}
            }
        }
    }

    private fun buildLoadingState() {
        loading {
            id(EpoxyViewsIds.LOADING.name)
            spanSizeOverride { totalSpanCount, _, _ ->
                totalSpanCount
            }
        }
    }

    private fun buildErrorState() {
        error {
            id(EpoxyViewsIds.ERROR.name)
            spanSizeOverride { totalSpanCount, _, _ ->
                totalSpanCount
            }
        }
    }

    private fun buildSuccessState(components: List<Component>) {
        components.map { component ->
            when (component.componentType) {
                ComponentsType.TOOLBAR -> toolbar {
                    id(component.id ?: "")
                    title(component.content?.title ?: "")
                    spanSizeOverride { _, _, _ ->
                        component.spanCount ?: 1
                    }
                }
                ComponentsType.VERTICAL_SPACE -> space {
                    id(component.id ?: "")
                    height(component.properties?.height?.toFloat() ?: 0f)
                    spanSizeOverride { _, _, _ ->
                        component.spanCount ?: 1
                    }
                }
                ComponentsType.TEXT_BUTTON -> textButton {
                    id(component.id ?: "")
                    background(component.properties?.color ?: "")
                    textColor(component.properties?.textColor ?: "")
                    marginStart(component.properties?.marginStart ?: 0)
                    marginEnd(component.properties?.marginEnd ?: 0)
                    text(component.content?.text ?: "")
                    clickListener {
                        this@MainController.buttonClickListener.invoke(
                            component.actions?.onClick?.clickType ?: ActionTypes.UNKNOWN,
                            component.actions?.onClick?.data?.url ?: ""
                        )
                    }
                    spanSizeOverride { _, _, _ ->
                        component.spanCount ?: 1
                    }
                }
                ComponentsType.FOOTER, ComponentsType.TEXT_VIEW, ComponentsType.IMAGE_VIEW, ComponentsType.UNKNOWN -> {}
                ComponentsType.COMPLEX_TOOLBAR -> complexToolbar {
                    id(component.id ?: "")
                    toolbarColor(component.properties?.color)
                    textColor(component.properties?.textColor)
                    title(component.content?.title)
                    icon(component.content?.icon)
                    padding(component.properties?.padding)
                    spanSizeOverride { _, _, _ ->
                        component.spanCount ?: 1
                    }
                }
                ComponentsType.CAROUSEL -> carousel {
                    id(component.id ?: "")
                    numViewsToShowOnScreen(1f)
                    paddingDp(component.properties?.padding ?: 0)
                    component.content?.items?.map {
                        it.toUI()
                    }?.also {
                        it.find { it is ClickableModel }?.apply {
                            (this as ClickableModel).initClickListener(this@MainController)
                        }
                        models(it)
                    }
                    spanSizeOverride { _, _, _ ->
                        component.spanCount ?: 1
                    }
                }
                ComponentsType.CARD -> {}
                else -> {}
            }
        }
    }

    override fun onButtonClicked(action: ActionTypes, uri: String) {
        buttonClickListener.invoke(action, uri)
    }
}