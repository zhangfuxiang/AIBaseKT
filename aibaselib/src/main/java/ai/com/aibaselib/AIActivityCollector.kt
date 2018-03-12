package ai.com.aibaselib

import android.app.Activity

/**
 * Created by Fuxiang.Zhang on 2018/3/6.
 */
open class AIActivityCollector {
    var mActivities: ArrayList<Activity>

    constructor() {
        this.mActivities = ArrayList<Activity>()
    }

    companion object {
        internal lateinit var instance: AIActivityCollector

        fun getInstance(): AIActivityCollector {
            if (Companion.instance == null) {
                Companion.instance = AIActivityCollector()
            }
            return Companion.instance
        }
    }

    fun addActivity(activity: Activity) {
        mActivities.add(activity)
    }

    fun removeActivity(activity: Activity) {
        mActivities.remove(activity)
    }

    fun finishAll() {
        for (activity: Activity in mActivities) {
            if (!activity.isFinishing()) {
                activity.finish()
            }
        }
    }

    fun backToRootActivity() {
        for ((index) in mActivities.withIndex()) {
            var activity: Activity = mActivities.get(index)
            activity.finish()
        }
    }

    fun rootActivity(): Activity {
        return mActivities.get(0)
    }

}

