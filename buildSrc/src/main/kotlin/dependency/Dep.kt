package dependency

object Dep {

    private const val KOTLIN_VERSION = "1.4.30"
    private const val OKHTTP_VERSION = "4.9.1"
    private const val RETROFIT_VERSION = "2.9.0"

    object GradlePlugin {
        const val androidGradlePlugin = "com.android.tools.build:gradle:4.1.2"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${KOTLIN_VERSION}"
    }

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:1.1.0"
        const val lifecycleExtension = "androidx.lifecycle:lifecycle-extensions:2.0.0"
    }

    const val material = "com.google.android.material:material:1.1.0"

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$KOTLIN_VERSION"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3"
    }

    object OkHttp {
        const val okhttp3 = "com.squareup.okhttp3:okhttp:$OKHTTP_VERSION"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$OKHTTP_VERSION"
    }

    object Retrofit {
        const val retrofit2 = "com.squareup.retrofit2:retrofit:$RETROFIT_VERSION"
        const val simpleXmlConverter =
            "com.squareup.retrofit2:converter-simplexml:$RETROFIT_VERSION"
    }

    object Test {
        const val junit = "junit:junit:4.12"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:$OKHTTP_VERSION"
        const val kotlinTestJunit = "org.jetbrains.kotlin:kotlin-test-junit:$KOTLIN_VERSION"
        const val coreTesting = "androidx.arch.core:core-testing:2.1.0"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.3"
        const val robolectric = "org.robolectric:robolectric:4.1"
        const val mockito = "org.mockito:mockito-core:2.25.1"
    }
}