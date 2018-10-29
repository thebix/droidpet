package net.thebix.launch.developer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import net.thebix.common.IoScheduler
import net.thebix.common.android.DroidpetActivity
import net.thebix.launch.R
import net.thebix.launch.developer.di.DeveloperComponent
import net.thebix.launch.developer.navigation.DeveloperFragmentNavigator
import javax.inject.Inject

class DeveloperFragment : Fragment() {

    private val buttonRepoList get() = view!!.findViewById(R.id.fragment_developer_repo_list_button) as Button

    @Inject
    lateinit var developerNavigator: DeveloperFragmentNavigator
    private lateinit var disposables: CompositeDisposable

    companion object {
        fun newInstance(): DeveloperFragment = DeveloperFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        ((activity as DroidpetActivity).getDroidpetComponentBuilder(DeveloperComponent::class.java)
                as DeveloperComponent.Builder)
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_developer, container, false)
    }

    override fun onStart() {
        super.onStart()
        disposables = CompositeDisposable(
            RxView.clicks(buttonRepoList)
                .subscribe {
                    developerNavigator.goToGithub()
                }
        )
    }

    override fun onStop() {
        disposables.clear()
        super.onStop()
    }

}
