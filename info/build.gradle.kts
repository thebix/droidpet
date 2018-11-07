plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(Config.AndroidConfig.compileSdkVersion)

    defaultConfig {
        minSdkVersion(Config.AndroidConfig.minSdkVersion)
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

    androidTestImplementation(Config.Libs.espressoCore)
    testImplementation(Config.Libs.junit)
    implementation(Config.Libs.kotlin)
    implementation(Config.Libs.kotlinReflect)
    implementation(Config.Libs.rxJava2)
    implementation(Config.Libs.rxBinding)
    implementation(Config.Libs.supportAppCompat)
    implementation(Config.Libs.supportFragment)
    androidTestImplementation(Config.Libs.supportTestRunner)
    implementation(Config.Libs.timber)

    debugImplementation(project(":common_android"))
    releaseImplementation(project(":common_android"))
}
