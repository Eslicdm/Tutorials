buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.47")
        classpath("com.android.tools.build:gradle:8.1.0")
        classpath("com.google.gms:google-services:4.3.15")
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.4")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.8")
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("com.android.library") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}