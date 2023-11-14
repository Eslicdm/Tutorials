import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("app.cash.sqldelight") version "2.0.0"
    id("com.google.devtools.ksp")
    kotlin("plugin.serialization") version "1.9.0"
    id("com.apollographql.apollo3") version "3.8.2"
}

android {
    compileSdk = 34

    defaultConfig {
        applicationId = "com.eslirodrigues.tutorials"
        minSdk = 21
        targetSdk = 34
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
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
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
    implementation("androidx.compose.foundation:foundation:1.6.0-alpha08")
    implementation("androidx.compose.material3:material3:1.2.0-alpha10")
    implementation("androidx.compose.material:material:1.6.0-alpha08")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.compose.ui:ui:1.5.4")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.4")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation("com.google.android.material:material:1.10.0")
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.4")

    // Test
    testImplementation("com.squareup.okhttp3:mockwebserver:4.10.0")
    testImplementation("com.google.truth:truth:1.1.5")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("junit:junit:4.13.2")
    testImplementation("io.mockk:mockk:1.13.8")
    testImplementation("io.ktor:ktor-client-mock:2.3.5")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.4")
    androidTestImplementation("androidx.navigation:navigation-testing:2.7.5")
    androidTestImplementation("com.google.truth:truth:1.1.5")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.4")

    // Animation
    implementation("androidx.compose.animation:animation-graphics-android:1.6.0-alpha08")
    implementation("androidx.compose.animation:animation:1.6.0-alpha08")

    // Lottie
    implementation("com.airbnb.android:lottie-compose:6.1.0")

    // Webkit
    implementation("androidx.webkit:webkit:1.8.0")

    // Window Manager
    implementation("androidx.window:window:1.1.0")

    // Adaptive Layout
    implementation("com.google.accompanist:accompanist-adaptive:0.27.0")

    // Window Size Material 3
    implementation("androidx.compose.material3:material3-window-size-class:1.1.2")


    // Ktor
    implementation("io.ktor:ktor-client-core:2.3.5")
    implementation("io.ktor:ktor-client-android:2.3.5")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.5")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.5")

    // Kotlin Serialization Json
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    // Apollo GraphQL
    implementation("com.apollographql.apollo3:apollo-runtime:3.8.2")

    // Permissions
    implementation("com.google.accompanist:accompanist-permissions:0.28.0")

    // Coil
    implementation("io.coil-kt:coil-compose:2.4.0")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Paging
    implementation("androidx.paging:paging-compose:3.2.1")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // OkHTTP
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    // Moshi
    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    // Room
    implementation("androidx.room:room-runtime:2.6.0")
    ksp("androidx.room:room-compiler:2.6.0")
    annotationProcessor("androidx.room:room-compiler:2.6.0")
    implementation("androidx.room:room-ktx:2.6.0")

    // SQL Delight
    implementation("app.cash.sqldelight:android-driver:2.0.0")
    implementation("app.cash.sqldelight:coroutines-extensions-jvm:2.0.0")

    // Firebase Firestore
    implementation("com.google.firebase:firebase-firestore-ktx:24.9.1")

    // Firebase Crashlytics
    implementation("com.google.firebase:firebase-crashlytics-ktx:18.5.1")
    implementation("com.google.firebase:firebase-analytics-ktx:21.5.0")

    // Firebase Messaging
    implementation("com.google.firebase:firebase-messaging-ktx:23.3.1")

    // Firebase Auth
    implementation("com.google.firebase:firebase-auth-ktx:22.2.0")

    // Firebase RealtimeDB
    implementation("com.google.firebase:firebase-database-ktx:20.3.0")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.5")

    // viewModel()
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    // Admob
    implementation("com.google.android.gms:play-services-ads:22.5.0")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    ksp("com.google.dagger:hilt-compiler:2.48.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    ksp("androidx.hilt:hilt-compiler:1.1.0")

    // Koin
    implementation("io.insert-koin:koin-androidx-compose:3.5.0")

    // Extended Icons
    implementation("androidx.compose.material:material-icons-extended:1.5.4")
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