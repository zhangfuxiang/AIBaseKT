package ai.com.aibaselib

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.FragmentActivity

/**
 * Created by Fuxiang.Zhang on 2018/3/6.
 */
class AIBaseFragmentActivity : FragmentActivity() {

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
}