package io.drdroid.chandra.utils

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.graphics.ColorUtils

object Utils {

    fun View.hideKeyboard() {
        val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    fun isDark(color: Int): Boolean {
        if (color == 0) {
            return false
        }
        return ColorUtils.calculateLuminance(color) < 0.5
    }

    fun setLightStatusBar(activity: Activity) {
        var flags = activity.window.decorView.systemUiVisibility
        flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        activity.window.decorView.systemUiVisibility = flags
    }

    fun clearLightStatusBar(activity: Activity) {
        var flags = activity.window.decorView.systemUiVisibility
        flags = flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        activity.window.decorView.systemUiVisibility = flags
    }

    fun View.colorTransition(endColor: Int, duration: Long = 250L) {
        var colorFrom = Color.TRANSPARENT
        if (background is ColorDrawable)
            colorFrom = (background as ColorDrawable).color

        val colorAnimation: ValueAnimator =
            ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, endColor)
        colorAnimation.duration = duration

        colorAnimation.addUpdateListener {
            if (it.animatedValue is Int) {
                val color = it.animatedValue as Int
                setBackgroundColor(color)
            }
        }
        colorAnimation.start()
    }
}