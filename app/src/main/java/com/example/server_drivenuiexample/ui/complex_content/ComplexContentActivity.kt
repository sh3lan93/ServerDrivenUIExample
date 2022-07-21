package com.example.server_drivenuiexample.ui.complex_content

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.server_drivenuiexample.R
import com.example.server_drivenuiexample.base.BaseActivity
import com.example.server_drivenuiexample.databinding.ActivityComplexContentBinding
import com.example.server_drivenuiexample.ui.design_system_language.ActionTypes
import com.example.server_drivenuiexample.ui.design_system_language.ComponentsType
import com.example.server_drivenuiexample.ui.models.Component
import com.example.server_drivenuiexample.ui.utils.ComponentClickListener
import com.example.server_drivenuiexample.ui.utils.PathsConstants
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComplexContentActivity :
    BaseActivity<ActivityComplexContentBinding, ComplexContentViewModel>(layout = R.layout.activity_complex_content) {

    override val viewModel: ComplexContentViewModel by viewModel()

    private val controller: ComplexScreenController by lazy {
        ComplexScreenController(onButtonClickListener, drawFooterCallback)
    }

    private val onButtonClickListener: ComponentClickListener = { type: ActionTypes, uri: String ->

    }

    private val drawFooterCallback: (component: Component) -> Unit = { component ->
        if (component.componentType == ComponentsType.TEXT_BUTTON) {
            val button = Button(this)
            button.layoutParams = ViewGroup.MarginLayoutParams(
                ViewGroup.MarginLayoutParams.MATCH_PARENT,
                ViewGroup.MarginLayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(
                    component.properties?.marginStart ?: 0,
                    0,
                    component.properties?.marginEnd ?: 0,
                    component.properties?.marginBottom ?: 0
                )

            }

            button.setBackgroundColor(Color.parseColor(component.properties?.color))
            button.text = component.content?.text
            button.setTextColor(Color.parseColor(component.properties?.textColor))

            button.setOnClickListener {
                if (component.actions?.onClick?.clickType == ActionTypes.TOAST)
                    Toast.makeText(this, component.actions.onClick.data?.text, Toast.LENGTH_SHORT)
                        .show()
            }
            (binding.root as FrameLayout).apply {
                addView(button)
                (button.layoutParams as FrameLayout.LayoutParams).gravity = Gravity.BOTTOM
            }
        }
    }

    override fun onCreateInit(savedInstanceState: Bundle?) {
        val spanCount =
            intent.data?.getQueryParameter(PathsConstants.COMPLEX_CONTENT_SCREEN_SPAN_COUNT)
                ?.toInt() ?: 0

        val jsonFileName =
            intent.data?.getQueryParameter(PathsConstants.COMPLEX_CONTENT_SCREEN_SCREEN_CONTENT_JSON)

        Log.d(
            ComplexContentActivity::class.java.simpleName,
            "onCreateInit: span count = $spanCount"
        )

        binding.rvContent.layoutManager = GridLayoutManager(this, spanCount)
        binding.rvContent.setController(controller)

        readJsonFile("api_response/${jsonFileName}")?.let {
            viewModel.mapJsonToScreenContent(it)
        }


        observerScreenContentResult()
    }

    private fun observerScreenContentResult() {
        viewModel.screenContentResult_.observe(this) {
            controller.screenContent = it
        }
    }
}