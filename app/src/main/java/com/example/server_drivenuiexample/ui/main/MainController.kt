package com.example.server_drivenuiexample.ui.main

import com.airbnb.epoxy.EpoxyController
import com.example.server_drivenuiexample.*
import com.example.server_drivenuiexample.states.Result
import com.example.server_drivenuiexample.states.ScreenState
import com.example.server_drivenuiexample.ui.design_system_language.ActionTypes
import com.example.server_drivenuiexample.ui.design_system_language.ComponentsType
import com.example.server_drivenuiexample.ui.main.epoxy.EpoxyViewsIds
import com.example.server_drivenuiexample.ui.models.Component
import com.example.server_drivenuiexample.ui.utils.ComponentClickListener

class MainController(
    private val buttonClickListener: ComponentClickListener
) :
    EpoxyController(
    ) {

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
        }
    }

    private fun buildErrorState() {
        error {
            id(EpoxyViewsIds.ERROR.name)
        }
    }

    private fun buildSuccessState(components: List<Component>) {
        components.map { component ->
            when (component.componentType) {
                ComponentsType.TOOLBAR -> toolbar {
                    id(component.id ?: "")
                    title(component.content?.title ?: "")
                }
                ComponentsType.VERTICAL_SPACE -> space {
                    id(component.id ?: "")
                    height(component.properties?.height?.toFloat() ?: 0f)
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
                }
                ComponentsType.UNKNOWN -> {}
            }
        }
    }
}