pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("com.android")) {
                useModule("com.android.tools.build:gradle:7.2.0")
            }
            if (requested.id.id.startsWith("org.jetbrains.kotlin")) {
                useVersion("1.7.10")
            }
            if (requested.id.id.startsWith("dagger.hilt.android")) {
                useModule("com.google.dagger:hilt-android-gradle-plugin:2.43.2")
            }
        }
    }
}

rootProject.name = "Learning_current"
include(":app")
