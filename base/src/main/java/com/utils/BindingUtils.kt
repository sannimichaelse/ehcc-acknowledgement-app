package com.utils

import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.utils.ext.clickWithDebounce

/**
 * Created by tomiwa on 09:14 2018-12-21
 */
object BindingUtils {

    interface OnKeyPressedListener {
        fun onKeyPressed()
    }

    @JvmStatic
    @BindingAdapter("onKeyDone")
    fun onKeyDone(editText: EditText, action: OnKeyPressedListener) {
        editText.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                action.onKeyPressed()
            }
            false
        }
    }

    @JvmStatic
    @BindingAdapter("clickWithDebounce")
    fun clickWithDebounce(view: View, OnClickWithDebounce: OnClickWithDebounce) {
        view.clickWithDebounce {
            OnClickWithDebounce.onClickWithDebounce()
        }
    }

    interface OnClickWithDebounce {
        fun onClickWithDebounce()
    }

    interface OnClickWithDebounceWithParams {
        fun onClickWithDebounce(view: View)
    }

    @JvmStatic
    @BindingAdapter("clickWithDebounceWithParams")
    fun clickWithDebounceWithParams(view: View, onClickWithDebounceWithParams: OnClickWithDebounceWithParams) {
        view.clickWithDebounce {
            onClickWithDebounceWithParams.onClickWithDebounce(it)
        }
    }
}