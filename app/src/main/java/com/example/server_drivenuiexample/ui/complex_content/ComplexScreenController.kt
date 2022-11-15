package com.example.server_drivenuiexample.ui.complex_content

import com.airbnb.epoxy.EpoxyController
import com.example.server_drivenuiexample.*
import com.example.server_drivenuiexample.states.Result
import com.example.server_drivenuiexample.states.ScreenState
import com.example.server_drivenuiexample.ui.design_system_language.ActionTypes
import com.example.server_drivenuiexample.ui.design_system_language.ComponentsType.*
import com.example.server_drivenuiexample.ui.main.epoxy.EpoxyViewsIds
import com.example.server_drivenuiexample.ui.models.Component
import com.example.server_drivenuiexample.ui.utils.ComponentClickListener

class ComplexScreenController(
    private val buttonClickListener: ComponentClickListener,
    private val drawFooterCallback: (component: Component) -> Unit
) :
    EpoxyController() {

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
                TOOLBAR -> toolbar {
                    id(component.id ?: "")
                    title(component.content?.title ?: "")
                    spanSizeOverride { totalSpanCount, _, _ ->
                        if (component.spanCount != null)
                            return@spanSizeOverride component.spanCount

                        totalSpanCount
                    }
                }
                VERTICAL_SPACE -> space {
                    id(component.id ?: "")
                    height(component.properties?.height?.toFloat() ?: 0f)

                    spanSizeOverride { totalSpanCount, _, _ ->
                        if (component.spanCount != null)
                            return@spanSizeOverride component.spanCount

                        totalSpanCount
                    }

                }
                TEXT_BUTTON -> textButton {
                    id(component.id ?: "")
                    background(component.properties?.color ?: "")
                    textColor(component.properties?.textColor ?: "")
                    marginStart(component.properties?.marginStart ?: 0)
                    marginEnd(component.properties?.marginEnd ?: 0)
                    text(component.content?.text ?: "")
                    clickListener {
                        this@ComplexScreenController.buttonClickListener.invoke(
                            component.actions?.onClick?.clickType ?: ActionTypes.UNKNOWN,
                            component.actions?.onClick?.data?.url ?: ""
                        )
                    }
                    spanSizeOverride { totalSpanCount, _, _ ->
                        if (component.spanCount != null)
                            return@spanSizeOverride component.spanCount

                        totalSpanCount
                    }
                }
                IMAGE_VIEW -> imageview {
                    id(component.id ?: "")
                    imageUrl(component.content?.url)
                    ratio(component.properties?.ratio)
                    spanSizeOverride { totalSpanCount, _, _ ->
                        if (component.spanCount != null)
                            return@spanSizeOverride component.spanCount

                        totalSpanCount
                    }
                }
                TEXT_VIEW -> textview {
                    id(component.id ?: "")
                    text(component.content?.text)
                    textColor(component.properties?.textColor)
                    marginStart(component.properties?.marginStart)
                    marginEnd(component.properties?.marginEnd)
                    marginTop(component.properties?.marginTop)
                    style(component.componentStyle)
                    spanSizeOverride { totalSpanCount, _, _ ->
                        if (component.spanCount != null)
                            return@spanSizeOverride component.spanCount
                        totalSpanCount
                    }
                }
                FOOTER -> component.content?.component?.let { footerComponent ->
                    drawFooterCallback.invoke(footerComponent)
                }
                UNKNOWN -> {}
                COMPLEX_TOOLBAR -> TODO()
                CAROUSEL -> TODO()
                CARD -> TODO()
                else -> {}
            }
        }
    }
}