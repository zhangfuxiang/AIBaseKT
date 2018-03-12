package ai.com.aibaselib

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

import ai.com.aibaselib.interfaces.AIGesturePasswordListener
import ai.com.aibaselib.interfaces.ActivityJumpListener


/**
 * Created by wuyoujian on 17/5/1.
 */

class ActivityConfig {
    private var mContext: Context? = null
    private val kSharedPreferencesKey_AlreadyGesturePWD = "kSharedPreferencesKey_DeviceId"
    //private long kDurTime = 1*1000;
    private var kDurTime = (3 * 60 * 1000).toLong()
    // 保存最后一次调用onPause()的系统时间戳
    var lockTime: Long = 0

    var gestureAnswer: String? = null//手势密码
    private var testModelOn = false
    var activityJumpListener: ActivityJumpListener? = null// 在子类中实现，并且是宿主app中
    var aiGesturePasswordListener: AIGesturePasswordListener? = null// 在子类中实现
    fun setTestModelOn(testModelOn: Boolean) {
        this.testModelOn = testModelOn
    }

    fun setDurTime(kDurTime: Long) {
        this.kDurTime = kDurTime
    }

    fun setmContext(mContext: Context) {
        this.mContext = mContext
    }

    fun setAlreadyGesturePassword() {
        // 从本地读取
        val sharedPreferences = mContext!!.getSharedPreferences("ActivityConfig",
                Activity.MODE_PRIVATE)
        //实例化SharedPreferences.Editor对象
        val editor = sharedPreferences.edit()
        val key = kSharedPreferencesKey_AlreadyGesturePWD
        // 保存到本地
        editor.putBoolean(key, true)
        editor.commit()
    }

    //实例化SharedPreferences.Editor对象
    val isAlreadyGesturePassword: Boolean
        get() {
            val sharedPreferences = mContext!!.getSharedPreferences("ActivityConfig",
                    Activity.MODE_PRIVATE)
            var isAlready = sharedPreferences.getBoolean(kSharedPreferencesKey_AlreadyGesturePWD, false)
            if (testModelOn) isAlready = true
            return isAlready
        }

    fun clearAlreadyGesturePassword() {
        val sharedPreferences = mContext!!.getSharedPreferences("ActivityConfig",
                Activity.MODE_PRIVATE)
        //实例化SharedPreferences.Editor对象
        val editor = sharedPreferences.edit()
        val key = kSharedPreferencesKey_AlreadyGesturePWD
        editor.putBoolean(key, false)
        editor.commit()
    }

    fun saveLockTime() {
        lockTime = System.currentTimeMillis()
    }

    // 需要结合是否有设置手势密码
    val isShowGesturePasswordActivity: Boolean
        get() {
            val durTime = System.currentTimeMillis() - lockTime
            if (durTime <= kDurTime || !isAlreadyGesturePassword) {
                return false
            }
            return true
        }

    companion object {
        internal lateinit var instance: ActivityConfig


        fun getInstance(): ActivityConfig {
            if (instance == null) {
                synchronized(ActivityConfig::class.java) {
                    instance = ActivityConfig()
                }
            }
            return instance
        }
    }
}


