plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}

android {
    val targetSdkVersion: Int by rootProject.extra
    val minSdkVersion: Int by rootProject.extra
    compileSdk = targetSdkVersion

    defaultConfig {
        applicationId = "com.fsm.pokedex"
        minSdk = minSdkVersion
        targetSdk = targetSdkVersion
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

//        Room stuff
//        javaCompileOptions {
//            annotationProcessorOptions {
//                arguments += mapOf(
//                    "room.schemaLocation" to "$projectDir/schemas",
//                    "room.incremental" to "true",
//                    "room.expandProjection" to "true"
//                )
//            }
//        }
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

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    namespace = "com.fsm.pokedex"
}

dependencies {
    val navigation = "2.7.6"
    val room = "2.6.1"
    val lifecycle = "2.7.0"
    val hilt = "2.50"
    val moshi = "1.15.1"
    val retrofit = "2.9.0"
    val paging = "3.2.1"
    val coroutines = "1.7.3"

    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines")

    //Hilt
    implementation("com.google.dagger:hilt-android:$hilt")
    ksp("com.google.dagger:hilt-compiler:$hilt")

    //Room
//    implementation("androidx.room:room-runtime:$room")
//    annotationProcessor("androidx.room:room-compiler:$room")
//    kapt("androidx.room:room-compiler:$room")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room")

    //Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:$navigation")
    implementation("androidx.navigation:navigation-ui-ktx:$navigation")
    // Testing Navigation
    androidTestImplementation("androidx.navigation:navigation-testing:$navigation")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofit")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofit")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")


    //Paging
    implementation("androidx.paging:paging-runtime-ktx:$paging")

    //Moshi
    implementation("com.squareup.moshi:moshi:$moshi")
    implementation("com.squareup.moshi:moshi:$moshi")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:$moshi")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}