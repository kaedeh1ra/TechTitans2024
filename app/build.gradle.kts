plugins {
    alias(libs.plugins.androidApplication)

    //Google Services
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.techtitans"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.techtitans"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.database)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)



    //Splash Screen
    implementation ("androidx.core:core-splashscreen:1.0.1")

    //Firebase & Google Services

    //Scalable Size Unit(support for different screen sizes)
    implementation ("com.intuit.sdp:sdp-android:1.1.1")
    implementation ("com.intuit.ssp:ssp-android:1.1.0")

    //Rounded ImageView
    implementation ("com.makeramen:roundedimageview:2.3.0")

    //Firebase
    implementation ("com.google.firebase:firebase-messaging:23.4.1")
    implementation (libs.firebase.firestore)

    //MultiDex
    implementation ("androidx.multidex:multidex:2.0.1")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-scalars:2.9.0")

    //MaskedET
    implementation("io.github.vicmikhailau:MaskedEditText:5.0.1")
}