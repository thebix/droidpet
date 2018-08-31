package net.thebix.github.repolist.mvi

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
        private val interactor: RepolistInteractor

) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == RepolistViewModel::class.java) {
            return RepolistViewModel(interactor) as T
        }
        throw IllegalArgumentException("unknown ViewModel class $modelClass")
    }
}
