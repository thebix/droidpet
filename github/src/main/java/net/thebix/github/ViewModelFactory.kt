package net.thebix.github

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import io.reactivex.Scheduler
import net.thebix.common.IoScheduler
import net.thebix.github.repolist.mvi.RepolistInteractor
import net.thebix.github.repolist.mvi.RepolistViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
        private val interactor: RepolistInteractor,
        @IoScheduler private val ioScheduler: Scheduler

) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == RepolistViewModel::class.java) {
            return RepolistViewModel(interactor, ioScheduler) as T
        }
        throw IllegalArgumentException("unknown ViewModel class $modelClass")
    }

}
