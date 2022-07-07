import kotlin.String

object Libs {
    /**
     * https://github.com/Kotlin/kotlinx.coroutines
     */
    const val kotlinx_coroutines_test: String =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:" +
            Versions.org_jetbrains_kotlinx_kotlinx_coroutines

    const val kotlinx_coroutines_core: String =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:" +
                Versions.org_jetbrains_kotlinx_kotlinx_coroutines

    /**
     * https://developer.android.com/jetpack/androidx/releases/swiperefreshlayout
     */
    const val swipe_refresh_layout: String =
        "androidx.swiperefreshlayout:swiperefreshlayout:" +
                Versions.androidx_swiperefreshlayout

    /**
     * https://developer.android.com/testing
     */
    const val espresso_contrib: String = "androidx.test.espresso:espresso-contrib:" +
            Versions.androidx_test_espresso

    /**
     * https://developer.android.com/testing
     */
    const val espresso_core: String = "androidx.test.espresso:espresso-core:" +
            Versions.androidx_test_espresso

    /**
     * https://developer.android.com/testing
     */
    const val espresso_idling_resource: String =
            "androidx.test.espresso:espresso-idling-resource:" + Versions.androidx_test_espresso

    /**
     * https://square.github.io/okhttp/
     */
    const val logging_interceptor: String = "com.squareup.okhttp3:logging-interceptor:" +
            Versions.com_squareup_okhttp3

    /**
     * https://square.github.io/okhttp/
     */
    const val mockwebserver: String = "com.squareup.okhttp3:mockwebserver:" +
            Versions.com_squareup_okhttp3

    /**
     * https://developer.android.com/topic/libraries/architecture/index.html
     */
    const val navigation_fragment_ktx: String =
            "androidx.navigation:navigation-fragment-ktx:" + Versions.androidx_navigation

    /**
     * https://developer.android.com/topic/libraries/architecture/index.html
     */
    const val navigation_safe_args_gradle_plugin: String =
            "androidx.navigation:navigation-safe-args-gradle-plugin:" + Versions.androidx_navigation

    /**
     * https://developer.android.com/topic/libraries/architecture/index.html
     */
    const val navigation_testing: String = "androidx.navigation:navigation-testing:" +
            Versions.androidx_navigation

    /**
     * https://developer.android.com/topic/libraries/architecture/index.html
     */
    const val navigation_ui: String = "androidx.navigation:navigation-ui:" +
            Versions.androidx_navigation

    /**
     * https://github.com/square/AssistedInject/
     */
    const val assisted_inject_annotations_dagger2: String =
            "com.squareup.inject:assisted-inject-annotations-dagger2:" +
            Versions.com_squareup_inject

    /**
     * https://github.com/square/AssistedInject/
     */

    const val hilt_android: String = "com.google.dagger:hilt-android:" +
            Versions.com_google_dagger

    const val hilt_android_compiler: String = "com.google.dagger:hilt-android-compiler:" +
            Versions.com_google_dagger

    const val hilt_android_gradle_plugin: String =
            "com.google.dagger:hilt-android-gradle-plugin:" + Versions.com_google_dagger

    const val hilt_android_testing: String = "com.google.dagger:hilt-android-testing:" +
            Versions.com_google_dagger

    const val hilt_compiler: String = "androidx.hilt:hilt-compiler:" + Versions.androidx_hilt

    /**
     * https://developer.android.com/testing
     */
    const val androidx_test_core_ktx: String = "androidx.test:core-ktx:" +
            Versions.androidx_test

    /**
     * https://developer.android.com/testing
     */
    const val androidx_test_rules: String = "androidx.test:rules:" + Versions.androidx_test

    /**
     * https://developer.android.com/testing
     */
    const val androidx_test_runner: String = "androidx.test:runner:" + Versions.androidx_test

    /**
     * https://developer.android.com/studio
     */
    const val com_android_tools_build_gradle: String = "com.android.tools.build:gradle:" +
            Versions.com_android_tools_build_gradle

    /**
     * https://developer.android.com/jetpack/androidx
     */
    const val androidx_core_core_ktx: String = "androidx.core:core-ktx:" +
            Versions.androidx_core_core_ktx

    /**
     * http://mockk.io
     */
    const val io_mockk_mockk: String = "io.mockk:mockk:" + Versions.io_mockk_mockk

    /**
     * https://kotlinlang.org/
     */
    const val kotlin_annotation_processing_gradle: String =
            "org.jetbrains.kotlin:kotlin-annotation-processing-gradle:" +
            Versions.kotlin_annotation_processing_gradle

    /**
     * https://kotlinlang.org/
     */
    const val kotlin_parcelize_compiler: String =
            "org.jetbrains.kotlin:kotlin-parcelize-compiler:" + Versions.kotlin_parcelize_compiler

    /**
     * https://kotlinlang.org/
     */
    const val kotlin_parcelize_runtime: String =
            "org.jetbrains.kotlin:kotlin-parcelize-runtime:" + Versions.kotlin_parcelize_runtime

    /**
     * https://kotlinlang.org/
     */
    const val kotlin_gradle_plugin: String = "org.jetbrains.kotlin:kotlin-gradle-plugin:" +
            Versions.kotlin_gradle_plugin

    /**
     * https://developer.android.com/topic/libraries/architecture/index.html
     */
    const val lifecycle_extensions: String = "androidx.lifecycle:lifecycle-extensions:" +
            Versions.lifecycle_extensions

    /**
     * https://kotlinlang.org/
     */
    const val kotlin_stdlib_jdk7: String = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:" +
            Versions.kotlin_stdlib_jdk7

    /**
     * https://kotlinlang.org/
     */
    const val kotlin_test_junit: String = "org.jetbrains.kotlin:kotlin-test-junit:" +
            Versions.kotlin_test_junit

    /**
     * http://tools.android.com
     */
    const val constraintlayout: String = "androidx.constraintlayout:constraintlayout:" +
            Versions.constraintlayout

    /**
     * https://developer.android.com/jetpack/androidx/releases/fragment#1.3.2
     */
    const val fragment_testing: String = "androidx.fragment:fragment-testing:" +
            Versions.fragment_testing

    /**
     * https://github.com/square/retrofit
     */
    const val converter_gson: String = "com.squareup.retrofit2:converter-gson:" +
            Versions.converter_gson

    /**
     * http://mockk.io
     */
    const val mockk_android: String = "io.mockk:mockk-android:" + Versions.mockk_android

    /**
     * https://developer.android.com/topic/libraries/architecture/index.html
     */
    const val core_testing: String = "androidx.arch.core:core-testing:" +
            Versions.core_testing

    /**
     * https://developer.android.com/studio
     */
    const val lint_gradle: String = "com.android.tools.lint:lint-gradle:" +
            Versions.lint_gradle

    const val viewbinding: String = "androidx.databinding:viewbinding:" +
            Versions.viewbinding

    /**
     * https://developer.android.com/jetpack/androidx
     */
    const val appcompat: String = "androidx.appcompat:appcompat:" + Versions.appcompat

    /**
     * https://developer.android.com/testing
     */
    const val junit_ktx: String = "androidx.test.ext:junit-ktx:" + Versions.junit_ktx

    /**
     * https://github.com/material-components/material-components-android
     */
    const val material: String = "com.google.android.material:material:" + Versions.material

    const val kluent: String = "org.amshove.kluent:kluent:" + Versions.kluent

    /**
     * https://developer.android.com/studio
     */
    const val aapt2: String = "com.android.tools.build:aapt2:" + Versions.aapt2

    /**
     * https://github.com/bumptech/glide
     */
    const val glide: String = "com.github.bumptech.glide:glide:" + Versions.glide

    /**
     * http://junit.org
     */
    const val junit: String = "junit:junit:" + Versions.junit
}
