package net.thebix.droidpet.github.api.di

import dagger.Component
import net.thebix.droidpet.github.api.GithubService

@GithubScope
@Component(
    modules = [
        GithubModule::class
    ]
)
interface GithubComponent {

    fun getGithubService(): GithubService
}
