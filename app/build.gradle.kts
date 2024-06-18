plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.tickets"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tickets"
        minSdk = 24
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    api(project(":core:network"))
    api(project(":core:data"))
    api(project(":core:domain"))
    api(project(":core:model"))
    api(project(":core:common"))
    api(project(":core:designsystem"))

    api(project(":feature:short"))
    api(project(":feature:hotels"))
    api(project(":feature:tickets"))
    api(project(":feature:subscriptions"))
    api(project(":feature:profile"))

    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}