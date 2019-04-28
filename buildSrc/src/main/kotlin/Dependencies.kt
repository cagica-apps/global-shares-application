const val kotlinVersion = "1.3.31"

object BuildPlugins {
    object Versions {
        const val androidBuildToolsVersion = "3.5.0-alpha13"
        const val detekt = "1.0.0-RC14"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidBuildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"

    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val kotlinKapt = "kotlin-kapt"
    const val detekt = "io.gitlab.arturbosch.detekt"
}

object AndroidSdk {
    const val min = 21
    const val compile = 28
    const val target = compile
}

object ProjectModules {
    const val core = ":core"
}

object Libraries {
    private object Versions {
        const val jetpack = "1.1.0-alpha04"
        const val constraintLayout = "2.0.0-alpha5"
        const val lifecycleExtensions = "2.1.0-alpha04"
        const val ktx = "1.1.0-alpha05"
        const val cardView = "1.0.0"
        const val rxJava = "2.2.8"
        const val rxAndroid = "2.1.1"
        const val retrofit = "2.5.0"
        const val gson = "2.8.5"
        const val okHttpLogging = "3.14.1"
        const val material = "1.1.0-alpha05"
    }

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.jetpack}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val ktxCore = "androidx.core:core-ktx:${Versions.ktx}"
    const val cardView = "androidx.cardview:cardview:${Versions.cardView}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.jetpack}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensions}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensions}"
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val rxjavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okHttpLoggingIntercaptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLogging}"
}

object TestLibraries {
    private object Versions {
        const val junit4 = "4.13-beta-2"
        const val mockk = "1.9.3"
        const val coreTesting = "2.1.0-alpha02"
    }
    const val junit4 = "junit:junit:${Versions.junit4}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTesting}"
}