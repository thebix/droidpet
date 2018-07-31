package net.thebix.droidpet.navigation

import android.content.Intent
import android.support.v4.app.FragmentActivity
import timber.log.Timber
import java.lang.ref.WeakReference
import java.security.InvalidKeyException
import javax.inject.Inject

interface NavigatorHolder {

    fun setNavigator(scope: Any, navigator: Navigator)
    fun getNavigator(scope: Any): Navigator
    fun removeNavigator(scope: Any)
}

class NavigatorHolderImpl @Inject constructor() : NavigatorHolder {

    private val navigators: HashMap<Any, Navigator> = HashMap()

    override fun setNavigator(scope: Any, navigator: Navigator) {
        if (navigators[scope] != null) {
            Timber.w("Rewriting existing navigator, scope: $scope, navigator: $navigator")
        }
        navigators[scope] = navigator
    }

    override fun getNavigator(scope: Any): Navigator {
        return navigators[scope] ?: throw Exception(InvalidKeyException("Can't get navigator for scope <$scope>"))
    }

    override fun removeNavigator(scope: Any) {
        navigators.remove(scope)
    }

}

interface Navigator {

    fun openActivity(intent: Intent)
    fun replaceFragment()
    fun addFragment()
    fun goBack()
}

class NavigatorImpl(fragmentActivity: FragmentActivity) : Navigator {

    private val activityRef = WeakReference<FragmentActivity>(fragmentActivity)

    override fun openActivity(intent: Intent) {
        val activity = activityRef.get()
        if (activity == null) {
            Timber.e("Can't openActivity since current activity was removed from navigator. Intent <$intent>")
            return
        }
        activity.startActivity(intent)
    }

    override fun replaceFragment() {
        // TODO: replace fragment
    }

    override fun addFragment() {
        // TODO: add fragment
    }

    override fun goBack() {
        // TODO: go back
    }

}
