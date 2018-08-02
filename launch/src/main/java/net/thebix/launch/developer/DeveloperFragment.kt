package net.thebix.launch.developer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.disposables.CompositeDisposable
import net.thebix.common_android.DroidpetActivity
import net.thebix.common_android.NavigatorHolder
import net.thebix.common_android.NavigatorImpl
import net.thebix.launch.R
import net.thebix.launch.developer.di.DeveloperComponent
import net.thebix.launch.developer.navigation.DeveloperFragmentNavigator
import net.thebix.launch.developer.navigation.DeveloperFragmentNavigator.Companion.scope
import javax.inject.Inject

class DeveloperFragment : Fragment() {

    private val buttonRepoList get() = view!!.findViewById(R.id.fragment_developer_repo_list_button) as Button

    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    @Inject
    lateinit var developerNavigator: DeveloperFragmentNavigator
    private lateinit var disposables: CompositeDisposable

    companion object {
        fun newInstance(): DeveloperFragment = DeveloperFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ((activity as DroidpetActivity).getDroidpetComponentBuilder(DeveloperComponent::class.java)
                as DeveloperComponent.Builder)
            .build()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_developer, container, false)
    }

    override fun onStart() {
        super.onStart()
        navigatorHolder.setNavigator(scope, NavigatorImpl(activity as FragmentActivity))
        disposables = CompositeDisposable(
            RxView.clicks(buttonRepoList)
                .subscribe {
                    developerNavigator.goToGithub()
                }
        )
    }

    override fun onStop() {
        navigatorHolder.removeNavigator(DeveloperFragment::class)
        disposables.clear()
        super.onStop()
    }

}
