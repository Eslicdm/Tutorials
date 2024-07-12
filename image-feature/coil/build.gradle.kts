@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.eslirodrigues.image_feature.coil"
    compileSdk = libs.versions.compile.target.sdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.min.sdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = libs.versions.java.get()
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // Core
    implementation(libs.bundles.compose.ui.material.foundation.core.lifecycle.activity)
    debugImplementation(libs.compose.ui.tooling)

    // Coil
    implementation(libs.coil.compose)
}