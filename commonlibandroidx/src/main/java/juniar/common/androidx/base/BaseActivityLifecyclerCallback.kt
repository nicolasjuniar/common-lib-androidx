package juniar.common.androidx.base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import juniar.common.androidx.utils.logInfo

class BaseActivityLifecyclerCallback(private val activity: Activity) : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        if (this.activity == activity) {
            "onCreate(${savedInstanceState.toString()})".logInfo(activity.localClassName)
        }
    }

    override fun onActivityStarted(activity: Activity) {
        if (this.activity == activity) {
            "onStart()".logInfo(activity.localClassName)
        }
    }

    override fun onActivityResumed(activity: Activity) {
        if (this.activity == activity) {
            "onResume()".logInfo(activity.localClassName)
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {
        if (this.activity == activity) {
            "onSaveInstanceState(${outState.toString()})".logInfo(activity.localClassName)
        }
    }

    override fun onActivityPaused(activity: Activity) {
        if (this.activity == activity) {
            "onPause()".logInfo(activity.localClassName)
        }
    }

    override fun onActivityStopped(activity: Activity) {
        if (this.activity == activity) {
            "onPause()".logInfo(activity.localClassName)
        }
    }

    override fun onActivityDestroyed(activity: Activity) {
        if (this.activity == activity) {
            "onDestroy()".logInfo(activity.localClassName)
            activity.application.unregisterActivityLifecycleCallbacks(this)
        }
    }
}