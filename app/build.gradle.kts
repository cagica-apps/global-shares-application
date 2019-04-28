plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.detekt) version (BuildPlugins.Versions.detekt)
}

android {
    compileSdkVersion(AndroidSdk.compile)
    dataBinding.isEnabled = true

    defaultConfig {
        applicationId ="pt.cagica.globalsharesapplication"
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BASE_URL", "\"http://developerexam.equityplansdemo.com/test/\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(project(ProjectModules.core))
    // android support libraries
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.appCompat)
    implementation(Libraries.ktxCore)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.cardView)
    implementation(Libraries.recyclerView)
    implementation(Libraries.material)
    // architecture
    implementation(Libraries.lifecycleExtensions)
    kapt(Libraries.lifecycleCompiler)
    // rxjava
    implementation(Libraries.rxJava)
    implementation(Libraries.rxAndroid)
    // retrofit
    implementation(Libraries.retrofit)
    implementation(Libraries.gson)
    implementation(Libraries.gsonConverter)
    implementation(Libraries.rxjavaAdapter)
    implementation(Libraries.okHttpLoggingIntercaptor)
    // test
    testImplementation(TestLibraries.coreTesting)
    testImplementation(TestLibraries.mockk)
    testImplementation(TestLibraries.junit4)
}

detekt {
    version = BuildPlugins.Versions.detekt
    input = files("src/main/java", "src/androidx/java", "src/support/java")
    filters = ".*test.*,.*/resources/.*,.*/tmp/.*"
}
