package com.eldorne.workshop.helper

import android.content.Context
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager

object WindowsHelper {

    /*
    ************************************************************************************************
    ** Public fun
    ************************************************************************************************
     */
    fun setStatusBarTranslucent(window: Window,
                                topView: View,
                                makeTranslucent: Boolean) {
        if (isKitkatOrOver()) {
            if (makeTranslucent) {
                //Make status bar translucent
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

                //Set padding to the root
                val context = topView.getContext()
                val statusBarHeight = getStatusBarHeight(context)

                // Set the padding to match the Status Bar height
                topView.setPadding(0, statusBarHeight, 0, 0)
            } else {
                //Remove  status bar translucent
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

                //Unset the padding
                topView.setPadding(0, 0, 0, 0)
            }
        }
    }

    /*
    ************************************************************************************************
    ** Private fun
    ************************************************************************************************
     */
    private fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        var result = 0
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    private fun isKitkatOrOver(): Boolean {
        return Build.VERSION.SDK_INT >= 19
    }
}