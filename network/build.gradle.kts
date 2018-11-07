plugins {
    java
    id("kotlin")
    id("kotlin-kapt")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to arrayOf("*.jar"))))

    implementation(Config.Libs.dagger)
    kapt(Config.Libs.daggerCompiler)
    implementation(Config.Libs.kotlin)
    implementation(Config.Libs.kotlinReflect)
    implementation(Config.Libs.okhttp)
    implementation(Config.Libs.okhttpLoggingInterceptor)
    implementation(Config.Libs.retrofit)
    implementation(Config.Libs.retrofitConverterJson)
    implementation(Config.Libs.retrofitAdapterRxJava)
    implementation(Config.Libs.rxJava2)
    implementation(Config.Libs.slf4j)
    implementation(Config.Libs.slf4jTimber)
    implementation(Config.Libs.timber)

    api(project(":network:network-api"))
    api(project(":network:network-impl"))
}
