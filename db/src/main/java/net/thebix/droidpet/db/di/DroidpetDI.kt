package net.thebix.droidpet.db.di

import dagger.Component
import dagger.Module
import dagger.Provides
import net.thebix.common.DroidpetComponent
import net.thebix.common.DroidpetComponentBuilder
import net.thebix.common.di.CommonComponent

@Component(
    modules = []
)
interface DbComponent : DroidpetComponent {

    @Component.Builder
    interface Builder : DroidpetComponentBuilder<DbComponent> {
        fun commonComponent(commonComponent: CommonComponent): Builder
        override fun build(): DbComponent
    }



}

@Module
interface GithubDbModule{

//    @Provides
//    fun provideGithubDao() =
}
