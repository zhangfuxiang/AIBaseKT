package ai.com.aibaselib

import ai.com.aibaselib.interfaces.ActivityJumpListener
import ai.com.aibaselib.util.LogUtil
import ai.com.aibaselib.util.PermissionUitls
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by Fuxiang.Zhang on 2018/3/6.
 */
open abstract class AIBaseActivity : AppCompatActivity() {

    val mEnbleGesturePwd: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        AIActivityCollector.getInstance().addActivity(this)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        AIActivityCollector.getInstance().removeActivity(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionUitls.getInstance()
        PermissionUitls.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

    //用来控制应用前后台切换的逻辑
    var isCurrentRunningForeground: Boolean = true

    override fun onStart() {
        super.onStart()
        if (!isCurrentRunningForeground) {
            if (ActivityConfig.getInstance().isAlreadyGesturePassword
                    && !(getClassName().equals("com.ai.aiportal.gesture.AIGesturePasswordActivity"))
                    && mEnbleGesturePwd) {
                var activityJumpListener: ActivityJumpListener
                        = ActivityConfig.getInstance().activityJumpListener!!
                if (activityJumpListener != null) {
                    activityJumpListener.jumpToAILocGesturePasswordActivity()
                } else {
                    var intent: Intent = Intent()
                    intent.setComponent(ComponentName("com.ai.aiportal", "com.ai.aiportal.gesture.AIGesturePasswordActivity"))
                    startActivity(intent)
                }
            }
            LogUtil.d("song", ">>>>>>>>>>>>>>>>>>>切到前台 activity process")

        }
    }

    override fun onStop() {
        super.onStop()
        isCurrentRunningForeground = isRunningForeground()
        if (!isCurrentRunningForeground) {
            ActivityConfig.getInstance().saveLockTime()
            LogUtil.d("song", ">>>>>>>>>>>>>>>>>>>切到后台 activity process")
        }
    }

    fun isRunningForeground(): Boolean {
        if (AIActivityLifecycleListener.getInstance().getRefCount() == 0) {
            return false
        }
        return true
    }


    private fun getClassName(): String {
        val contextString: String = this.toString()
        return contextString.substring(0, contextString.indexOf("@"))
    }
}