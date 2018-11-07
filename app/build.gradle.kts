plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(Config.AndroidConfig.compileSdkVersion)

    defaultConfig {
        minSdkVersion(Config.AndroidConfig.minSdkVersion)
        applicationId = "net.thebix.droidpet"
        targetSdkVersion(Config.AndroidConfig.targetSdkVersion)
        versionCode = 1
        versionName = "0.0.1"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to arrayOf("*.jar"))))

    implementation(Config.Libs.dagger)
    androidTestImplementation(Config.Libs.espressoCore)
    kapt(Config.Libs.daggerCompiler)
    testImplementation(Config.Libs.junit)
    implementation(Config.Libs.kotlin)
    implementation(Config.Libs.kotlinReflect)
    debugImplementation(Config.Libs.leakCanary)
    releaseImplementation(Config.Libs.leakCanaryNoOp)
    debugImplementation(Config.Libs.leakCanarySupportFragment)
    implementation(Config.Libs.supportAppCompat)
    androidTestImplementation(Config.Libs.supportTestRunner)
    implementation(Config.Libs.timber)

    debugImplementation(project(":common"))
    releaseImplementation(project(":common"))
    debugImplementation(project(":common_android"))
    releaseImplementation(project(":common_android"))
    debugImplementation(project(":launch"))
    releaseImplementation(project(":launch"))
    debugImplementation(project(":info"))
    releaseImplementation(project(":info"))
    debugImplementation(project(":github"))
    releaseImplementation(project(":github"))
}
