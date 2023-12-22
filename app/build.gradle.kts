import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id(libs.plugins.dagger.hilt.android.get().pluginId)
    id(libs.plugins.gms.google.services.get().pluginId)
    id(libs.plugins.firebase.crashlytics.get().pluginId)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.apollo.graphql)
}

android {
    compileSdk = libs.versions.compile.target.sdk.get().toInt()

    defaultConfig {
        applicationId = "com.eslirodrigues.tutorials"
        minSdk = libs.versions.min.sdk.get().toInt()
        targetSdk = libs.versions.compile.target.sdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val apiKey = gradleLocalProperties(rootDir).getProperty("API_KEY")
        buildConfigField("String", "API_KEY", apiKey)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
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
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.asProvider().get()
    }
    packaging {
        resources {
            excludes.apply {
                add("/META-INF/{AL2.0, LGPL2.1}")
                add("META-INF/versions/9/previous-compilation-data.bin")
            }
        }
    }
    namespace = "com.eslirodrigues.tutorials"
}

dependencies {
    // Modules
    implementation(project(":image-feature:coil"))

    // Core
    implementation(libs.bundles.compose.ui.material.foundation.core.lifecycle.activity)
    debugImplementation(libs.compose.ui.tooling)

    // Test
    testImplementation(libs.bundles.test)
    debugImplementation(libs.compose.ui.test.manifest)
    androidTestImplementation(libs.bundles.androidTest)

    // Animation
    implementation(libs.bundles.compose.animation)

    // Lottie
    implementation(libs.lottie.compose)

    // Webkit
    implementation(libs.webkit)

    // Window Manager
    implementation(libs.window)

    // Window Size Material 3
    implementation(libs.material3.window.size)

    // Accompanist - Permissions, Adaptive Layout
    implementation(libs.bundles.accompanist)

    // Ktor
    implementation(libs.bundles.ktor)

    // Kotlin Serialization Json
    implementation(libs.kotlinx.serialization.json)

    // Apollo GraphQL
    implementation(libs.apollo.graphql.runtime)

    // Coil
    implementation(libs.coil.compose)

    // DataStore Preferences
    implementation(libs.datastore.preferences)

    // Paging
    implementation(libs.paging.compose)

    // Retrofit
    implementation(libs.bundles.retrofit)

    // OkHTTP
    implementation(libs.bundles.okhttp)

    // Moshi
    implementation(libs.bundles.moshi)

    // Room
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)
    annotationProcessor(libs.room.compiler)

    // SQL Delight
    implementation(libs.bundles.sqldelight)

    // Firebase - Firestore, Crashlytics, Messaging, Auth, RealtimeDB
    implementation(libs.bundles.firebase)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // viewModel()
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // Admob
    implementation(libs.admob)

    // Hilt
    implementation(libs.bundles.hilt)
    ksp(libs.bundles.hilt.compiler)

    // Koin
    implementation(libs.koin.androidx.compose)

    // Extended Icons
    implementation(libs.androidx.material.icons.extended)
}

sqldelight {
    databases {
        create("TutorialSqlDelightDatabase") {
            packageName.set("com.eslirodrigues.tutorials")
        }
    }
}

apollo {
    service("serviceName") {
        packageName.set("com.eslirodrigues.tutorials")
    }
}
// ./gradlew :app:downloadApolloSchema --endpoint='https://beta.pokeapi.co/graphql/v1beta' --schema=app/src/main/graphql/schema.graphqls