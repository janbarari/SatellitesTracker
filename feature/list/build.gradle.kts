import dependency.local

plugins {
    androidLibrary
    kotlinAndroid
    kotlinKapt
    hilt
    navigationComponent
}

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 23
        targetSdk = 31
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        baseAndroidx()
        Coroutines()
        Junit()
        MaterialDesign()
        Hilt()
        NavigationComponent()
        ConstraintLayout()
        Mockk()
        Core()
        Data()
        Domain()
    }


}