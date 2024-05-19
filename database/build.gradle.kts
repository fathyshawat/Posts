@file:Suppress("UnstableApiUsage", "DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.plugin)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
}

android {
    compileSdk = 34
    namespace = "com.example.postsapp.database"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    lint {
        quiet = true
        abortOnError = false
        warningsAsErrors = true
        disable += "Instantiatable"
    }

    kotlinOptions.jvmTarget = "17"
    buildFeatures.buildConfig = true
}

dependencies {
    // Hilt
    implementation(libs.hilt)
    kapt(libs.hiltDaggerCompiler)
    implementation(libs.coroutinesCore)
    implementation(libs.roomKts)
    implementation(libs.roomCoroutine)
    annotationProcessor(libs.roomCompiler)
    kapt(libs.roomCompiler)
}