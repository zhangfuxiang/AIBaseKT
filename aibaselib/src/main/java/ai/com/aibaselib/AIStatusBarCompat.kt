package ai.com.aibaselib

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup

/**
 * Created by Fuxiang.Zhang on 2018/3/6.
 */
class AIStatusBarCompat {
    companion object {
        final val INVALID_VAL: Int = -1
        final val COLOR_DEFAULT: Int = Color.parseColor("#00000000");

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        fun compat(activity: Activity, statusColor: Int) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (statusColor != INVALID_VAL) {
                    activity.getWindow().setStatusBarColor(statusColor)
                }
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                var color: Int = COLOR_DEFAULT
                val contentView: ViewGroup = activity.findViewById(android.R.id.content)
                if (statusColor != INVALID_VAL) {
                    color = statusColor
                }
                val statusBarView = View(activity)
                val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        getStatusBarHeight(activity))
                statusBarView.setBackgroundColor(color)
                contentView.addView(statusBarView, lp)
            }


        }

        fun compat(activity: Activity) {
            compat(activity, INVALID_VAL)
        }

        private fun getStatusBarHeight(context: Context): Int {

            var result = 0
            val resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                result = context.getResources().getDimensionPixelSize(resourceId)
            }
            return result
        }


    }
}