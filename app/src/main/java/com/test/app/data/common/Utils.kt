package com.test.app.data.common

import android.content.Context

object Utils {

    const val INVALID = -1;

    fun pxFromDp(context: Context?, dp: Float): Float {
        return dp * context?.resources?.displayMetrics?.density!!
    }

    fun DpFromPx(context: Context?, px: Float): Float {
        return px / context?.resources?.displayMetrics?.density!!;
    }
}