plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id ("androidx.navigation.safeargs")
    id ("kotlin-parcelize")
    id ("com.google.gms.google-services")
    id ("com.google.firebase.crashlytics")
    id ("de.mannodermaus.android-junit5")  version("1.8.2.1")
}

android {
    namespace = "com.areeb.boxoffice"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.areeb.boxoffice"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "0.0.0"

        testInstrumentationRunner = "com.areeb.boxoffice.InstrumentationTestRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {



    // Support Dependencies for kotlin and UI
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    supportDependencies.values.forEach { implementation(it) }

    // UI Dependencies
    uiDependencies.values.forEach { implementation(it) }

    // Firebase Dependencies
    firebaseDependencies.values.forEach { implementation(it) }


    // Dependencies for local unit tests
    supportDependenciesTest.values.forEach { testImplementation(it) }
    supportDependenciesTestRuntime.values.forEach { testRuntimeOnly(it) }

    // Dependencies for Android Test
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    supportDependenciesAndroidTest.values.forEach { androidTestImplementation(it) }
    supportDependenciesDebugAndroidTest.values.forEach { debugImplementation(it) }

    // Network Client
    networkClientDependencies.values.forEach { implementation(it) }

    // Dependency Injection
    dependencyInjectionDependencies.values.forEach { implementation(it) }

    // Database
    databaseDependencies.values.forEach { implementation(it) }
    databaseAnnotationDependencies.values.forEach { kapt(it) }



}