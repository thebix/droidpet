// INFO: https://frogermcs.github.io/activities-multibinding-in-dagger-2/
// INFO: https://github.com/frogermcs/Dagger2Recipes-ActivitiesMultibinding/blob/master/app/src/main/java/com/frogermcs/recipes/dagger_activities_multibinding/di/activity/ActivityKey.java

package net.thebix.common

import dagger.MapKey
import kotlin.reflect.KClass

interface DroidpetComponent

@MapKey
annotation class DroidpetComponentKey(val value: KClass<out DroidpetComponent>)

interface DroidpetComponentBuilder<out Component : DroidpetComponent> {
    fun build(): Component
}

interface HasDroidpetSubcomponentBuilders {

    fun getDroidpetComponentBuilder(droidpetComponentClass: Class<out DroidpetComponent>): DroidpetComponentBuilder<*>

}
