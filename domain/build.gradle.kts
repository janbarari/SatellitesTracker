import dependency.local

plugins {
    androidLibrary
    kotlinAndroid
    kotlinParcelize
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
}

dependencies {

    local {
        Coroutines()
        Junit(enableAndroidTest = false)
        Hilt()
    }

}