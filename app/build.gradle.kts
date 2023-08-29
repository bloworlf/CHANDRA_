plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "io.drdroid.chandra"
    compileSdk = 34

    defaultConfig {
        applicationId = "io.drdroid.chandra"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.11")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.11")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.github.bumptech.glide:glide:4.15.1")
    implementation("jp.wasabeef:glide-transformations:4.3.0")
    implementation("jp.wasabeef:recyclerview-animators:4.0.2")

    implementation("androidx.palette:palette:1.0.0")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    implementation("com.android.databinding:viewbinding:8.1.1")
    implementation("androidx.fragment:fragment-ktx:1.7.0-alpha03")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.1")

    runtimeOnly("io.insert-koin:koin-core:3.4.3")
    implementation("io.insert-koin:koin-core-coroutines:3.4.3")
    implementation("io.insert-koin:koin-android:3.4.3")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //test coroutine
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.2")

    //mockito
    testImplementation("org.mockito:mockito-core:3.12.4")
//    androidTestImplementation "org.mockito:mockito-android:2.2.0"
    testImplementation("org.mockito:mockito-inline:3.11.2")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
}