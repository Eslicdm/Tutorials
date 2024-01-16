buildscript {
    dependencies {
        classpath(libs.bundles.project.level.gradle)
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
// Lists all plugins used throughout the project without applying them.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.devtools.ksp) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}