package dependency

object Dep {
    object GradlePlugin {
        const val androidGradlePlugin = "com.android.tools.build:gradle:4.1.2"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.30"
    }

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:1.1.0"
    }

    const val material = "com.google.android.material:material:1.1.0"

    object UnitTest {
        const val junit = "junit:junit:4.12"
    }

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:1.4.30"
    }
}