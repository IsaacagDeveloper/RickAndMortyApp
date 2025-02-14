buildscript {
    project.apply {
        from("$rootDir/common.gradle")
    }
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Libs.com_android_tools_build_gradle)
        classpath(Libs.kotlin_gradle_plugin)
        classpath(Libs.navigation_safe_args_gradle_plugin)
        classpath(Libs.hilt_android_gradle_plugin)
    }
}

allprojects {
    apply {
        from("$rootDir/common.gradle")
    }
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}