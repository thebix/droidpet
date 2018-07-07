package net.thebix.droidpet.github.repolist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import net.thebix.droidpet.R
import net.thebix.droidpet.github.api.GithubService
import net.thebix.droidpet.github.api.di.DaggerGithubComponent
import net.thebix.droidpet.github.api.models.Repo
import net.thebix.droidpet.github.repolist.di.DaggerRepolistComponent
import net.thebix.droidpet.network.di.DaggerNetworkComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class RepolistFragment : Fragment() {

    companion object {
        fun newInstance() = RepolistFragment()
    }

    private val repolistItems get() = view!!.findViewById(R.id.fragment_github_repolist_items) as TextView

    @Inject
    lateinit var githubService: GithubService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: move to application class
        val networkComponent = DaggerNetworkComponent.create()
        val githubComponent = DaggerGithubComponent.builder()
            .networkComponent(networkComponent)
            .build()
        val repolistComponent = DaggerRepolistComponent.builder()
            .githubComponent(githubComponent)
            .build()
        repolistComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_github_repolist, container, false)

    override fun onStart() {
        super.onStart()
        githubService.repoList("thebix")
            .enqueue(object : Callback<List<Repo>> {
                override fun onFailure(call: Call<List<Repo>>?, t: Throwable?) {
                    Timber.e(t)
                    repolistItems.setText(R.string.github_repolist_network_error)
                }

                override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                    val repos = response.body() ?: emptyList()
                    Timber.d("Received the list of repos, count: <${response.body()?.size}>")
                    repolistItems.text = repos.joinToString("\n") { it.name }
                }
            })
    }
}
