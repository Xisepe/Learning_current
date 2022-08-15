plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = Config.testInstrumentationRunner
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        kotlinCompilerExtensionVersion = Versions.compose
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

//    implementation("androidx.core:core-ktx:1.8.0")
//
//    implementation("androidx.compose.ui:ui:${Versions.compose}")
//    implementation("androidx.compose.material:material:${Versions.compose}")
//    implementation("androidx.compose.material:material-icons-extended:${Versions.compose}")
    //implementation("androidx.compose.ui:ui-tooling-preview:${Versions.compose}")

    //implementation("com.android.tools.build:gradle:7.2.0")

    //implementation("androidx.navigation:navigation-compose:${Versions.compose}")

    //implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
    //implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntime}")
    //implementation("androidx.activity:activity-compose:${Versions.compose}")

    //implementation("com.google.dagger:hilt-android:${Versions.hilt}")
    //kapt("com.google.dagger:hilt-compiler:${Versions.hilt}")

    //testImplementation("junit:junit:4.13.2")

    //androidTestImplementation("androidx.test.ext:junit:${Versions.extJunit}}")
    //androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.espressoCore}")
    //androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Versions.compose}")

    implementation(Dependencies.appLibs)
    kapt(Dependencies.kaptLibs)
    testImplementation(Dependencies.testLibs)
    androidTestImplementation(Dependencies.androidTestLibs)

}