import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {

    //android ui
    private const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntime}"
    private const val composeUI = "androidx.compose.ui:ui:${Versions.compose}"
    private const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    private const val composeMaterialIconsExtended = "androidx.compose.material:material-icons-extended:${Versions.compose}"
    private const val composePreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    private const val composeActivity = "androidx.activity:activity-compose:${Versions.compose}"

    //navigation
    private const val navigation = "androidx.navigation:navigation-compose:${Versions.compose}"

    //serialization
    private const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"

    //Dependency Injection
    private const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    private const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"

    //test
    private const val junit = "junit:junit:${Versions.junit}"
    private const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
    private const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    private const val composeJunit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"

    val appLibs = listOf(
        coreKtx,
        lifecycle,
        composeUI,
        composeMaterial,
        composeMaterialIconsExtended,
        composePreview,
        composeActivity,
        navigation,
        serialization,
        hilt
    )

    val kaptLibs = listOf(hiltCompiler)

    val testLibs = listOf(junit)

    val androidTestLibs = listOf(
        extJunit,
        espressoCore,
        composeJunit
    )

}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach{
        add("implementation", it)
    }
}

fun DependencyHandler.kapt(list: List<String>) {
    list.forEach{
        add("kapt", it)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach{
        add("testImplementation", it)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach{
        add("androidTestImplementation", it)
    }
}
