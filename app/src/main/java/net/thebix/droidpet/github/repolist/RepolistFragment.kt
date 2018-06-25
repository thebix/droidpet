package net.thebix.droidpet.github.repolist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import net.thebix.droidpet.R
import net.thebix.droidpet.github.api.GithubService
import net.thebix.droidpet.github.api.models.Repo
import net.thebix.droidpet.github.network.LiggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class RepolistFragment : Fragment() {

    companion object {
        fun newInstance() = RepolistFragment()

        private const val URL_GITHUB_API = "https://api.github.com/"
    }

    private val repolistItems get() = view!!.findViewById(R.id.fragment_github_repolist_items) as TextView

    private lateinit var retrofit: Retrofit
    private lateinit var okHttpLoggedClient: OkHttpClient
    private lateinit var githubService: GithubService
    private lateinit var gsonConverterFactory: GsonConverterFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        okHttpLoggedClient = OkHttpClient.Builder()
            .addInterceptor(LiggingInterceptor())
            .build()

        gsonConverterFactory = GsonConverterFactory.create()

        retrofit = Retrofit.Builder()
            .client(okHttpLoggedClient)
            .addConverterFactory(gsonConverterFactory)
            .baseUrl(URL_GITHUB_API)
            .build()
        githubService = retrofit.create(GithubService::class.java)
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
