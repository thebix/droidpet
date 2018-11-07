object Config {

    object Versions {
        const val archLifecycle = "1.1.1"
        const val dagger = "2.16"
        const val kotlin = "1.3.0"
        const val leakCanary = "1.6.1"
        const val okhttp = "3.10.0"
        const val retrofit = "2.3.0"
        const val supportLibrary = "27.1.1"
    }

    object AndroidConfig {
        const val compileSdkVersion = 27
        const val minSdkVersion = 23
        const val targetSdkVersion = 27
    }

    object Libs {
        const val archLifecycleExtensions = "android.arch.lifecycle:extensions:${Versions.archLifecycle}"
        const val archLifecycleViewModel = "android.arch.lifecycle:viewmodel:${Versions.archLifecycle}"
        const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
        const val espressoCore = "com.android.support.test.espresso:espresso-core:3.0.2"
        const val junit = "junit:junit:4.12"
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
        const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
        const val leakCanaryNoOp = "com.squareup.leakcanary:leakcanary-android-no-op:${Versions.leakCanary}"
        const val leakCanarySupportFragment = "com.squareup.leakcanary:leakcanary-support-fragment:${Versions.leakCanary}"
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitConverterJson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val retrofitAdapterRxJava = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
        const val rxBinding = "com.jakewharton.rxbinding2:rxbinding:2.1.1"
        const val rxJava2 = "io.reactivex.rxjava2:rxjava:2.1.15"
        const val slf4j = "org.slf4j:log4j-over-slf4j:1.7.21"
        const val slf4jTimber = "com.arcao:slf4j-timber:3.0"
        const val supportAppCompat = "com.android.support:appcompat-v7:${Versions.supportLibrary}"
        const val supportConstraint = "com.android.support.constraint:constraint-layout:1.1.2"
        const val supportFragment = "com.android.support:support-fragment:${Versions.supportLibrary}"
        const val supportTestRunner = "com.android.support.test:runner:1.0.2"
        const val timber = "com.jakewharton.timber:timber:4.7.0"
    }
}
