import dependency.global

plugins {
    androidApplication
    kotlinAndroid
    navigationComponent
    hilt
    kotlinKapt
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "io.github.janbarari.satellitestracker"
        minSdk = 23
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
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
    
    kapt {
        correctErrorTypes = true
    }

}

dependencies {

    global {
        Appcompat()
        Coroutines()
        Room()
        Junit()
        Espresso()
        ConstraintLayout()
        MaterialDesign()
        NavigationComponent()
        Hilt()
        Moshi()
        Feature_List()
        Feature_Details()
        Core()
        Data()
        Domain()
    }

}