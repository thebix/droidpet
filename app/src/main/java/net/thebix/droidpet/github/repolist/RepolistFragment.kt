package net.thebix.droidpet.github.repolist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.thebix.droidpet.R

class RepolistFragment : Fragment() {

    companion object {
        fun newInstance() = RepolistFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_github_repolist, container, false)
}
