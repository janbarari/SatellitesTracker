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

    //Disable the animations if any in order to avoid some other error types
    testOptions {
        animationsDisabled = true
    }

    //Block exclude the files from above, in order to avoid weird compilations errors
    packagingOptions {
        resources.excludes.add("META-INF/DEPENDENCIES")
        resources.excludes.add("META-INF/LICENSE")
        resources.excludes.add("META-INF/LICENSE.txt")
        resources.excludes.add("META-INF/license.txt")
        resources.excludes.add("META-INF/NOTICE")
        resources.excludes.add("META-INF/NOTICE.txt")
        resources.excludes.add("META-INF/notice.txt")
        resources.excludes.add("META-INF/ASL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/*.kotlin_module")
    }

}

dependencies {

    global {
        BaseAndroidx()
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
        Lifecycle()
        ViewModel()
    }

}