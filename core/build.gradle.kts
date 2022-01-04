import dependency.local

plugins {
    androidLibrary
    kotlinAndroid
    hilt
    kotlinKapt
}

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 23
        targetSdk = 31
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        viewBinding = true
    }

}

dependencies {

    local {
        BaseAndroidx()
        RecyclerView()
        Junit(isAndroidTestEnabled = false)
        Hilt(isAndroidTestEnabled = false)
    }

}