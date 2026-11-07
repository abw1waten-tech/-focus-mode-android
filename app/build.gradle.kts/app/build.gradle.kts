
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.fahd.focusmode"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.fahd.focusmode"
        minSdk = 26
        targetSdk = 28
        versionCode = 1
        versionName = "1.0"
    }
}

