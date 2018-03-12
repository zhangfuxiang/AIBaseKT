package ai.com.aibaselib

import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * Created by Fuxiang.Zhang on 2018/3/6.
 */
class AIActivityLifecycleListener : Application.ActivityLifecycleCallbacks {

    companion object {
        internal lateinit var instance: AIActivityLifecycleListener
        fun getInstance(): AIActivityLifecycleListener {
            if (instance == null) {
                synchronized(AIActivityLifecycleListener::class.java) {
                    instance = AIActivityLifecycleListener()
                }
            }
            return instance;
        }

    }

    private var refCount: Int = 0

    fun getRefCount(): Int {
        return refCount
    }

    override fun onActivityPaused(p0: Activity?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityResumed(p0: Activity?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityStarted(p0: Activity?) {
        refCount++
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityDestroyed(p0: Activity?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityStopped(p0: Activity?) {
        refCount--
        if (refCount == 0) {

        }
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityCreated(p0: Activity?, p1: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}