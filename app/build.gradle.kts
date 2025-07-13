// build.gradle.kts (:app)
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.gms.google-services") // Este plugin está bien así
}

android {
    namespace = "com.example.saludmental"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.saludmental"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // Dependencias existentes que ya usaban Version Catalog (están bien)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.common.android)

    // Dependencias de test (están bien)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Dependencias de navegación y Material Icons que ya estaban usando Version Catalog (están bien)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.material.icons.extended)

    // --- ¡Aquí están los cambios clave para Firebase y Lifecycle! ---

    // Firebase BOM - Administra versiones de Firebase
    // Antes: implementation(platform("com.google.firebase:firebase-bom:33.16.0"))
    implementation(platform(libs.firebase.bom)) // <-- CORREGIDO

    // Firebase Dependencies
    // Antes: implementation("com.google.firebase:firebase-analytics")
    implementation(libs.firebase.analytics) // <-- CORREGIDO
    // Antes: implementation("com.google.firebase:firebase-auth")
    implementation(libs.firebase.auth)       // <-- CORREGIDO
    // Antes: implementation("com.google.firebase:firebase-firestore")
    implementation(libs.firebase.firestore)  // <-- CORREGIDO

    // ViewModel y LiveData
    // Antes: implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.1")
    implementation(libs.androidx.lifecycle.viewmodel.compose) // <-- CORREGIDO
    // Antes: implementation("androidx.lifecycle:lifecycle-runtime-compose:2.9.1")
    implementation(libs.androidx.lifecycle.runtime.compose)   // <-- CORREGIDO
}