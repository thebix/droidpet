@file:Suppress("unused")

package net.thebix.common.di

import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import net.thebix.common.IoScheduler
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope

@ApplicationScope
@Component(
    modules = [
        CommonModule::class
    ]
)
interface CommonComponent {

    @IoScheduler
    fun getIoScheduler(): Scheduler
}

@Module
class CommonModule {

    @ApplicationScope
    @IoScheduler
    @Provides
    fun provideIoScheduler(): Scheduler = Schedulers.io()

}
