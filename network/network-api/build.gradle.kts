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
    implementation(Config.Libs.retrofit)
    implementation(Config.Libs.rxJava2)
}
