// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.39.1")


        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

extra["minSdkVersion"] = 21
extra["targetSdkVersion"] = 31

tasks.create("clean", Delete::class) {
    delete(rootProject.buildDir)
}