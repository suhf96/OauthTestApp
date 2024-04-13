plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    namespace = "com.gyadam.googleoauthtest"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.gyadam.googleoauthtest"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "CLIENT_ID_APP_NAME", "\"googleOauthTest\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            applicationIdSuffix = ".debug"
            buildConfigField(
                "String",
                "OAUTH_GMAIL_CLIENT_ID",
                "\"262622259280-5qb3vtj68d5dtudmaif4g9vd3cpar8r3.apps.googleusercontent.com\"",
            )
            buildConfigField(
                "String",
                "OAUTH_YAHOO_CLIENT_ID",
                "\"dj0yJmk9ejRCRU1ybmZjQlVBJmQ9WVdrOVVrZEViak4xYmxZbWNHbzlNQT09JnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PTZj\"",
            )
            buildConfigField(
                "String",
                "OAUTH_AOL_CLIENT_ID",
                "\"dj0yJmk9cHYydkJkTUxHcXlYJmQ9WVdrOWVHZHhVVXN4VVV3bWNHbzlNQT09JnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PTdm\"",
            )
            buildConfigField(
                "String",
                "OAUTH_MICROSOFT_CLIENT_ID",
                "\"e647013a-ada4-4114-b419-e43d250f99c5\""
            )
            buildConfigField(
                "String",
                "OAUTH_MICROSOFT_REDIRECT_URI",
                "\"msauth://com.fsck.k9.debug/VZF2DYuLYAu4TurFd6usQB2JPts%3D\"",
            )
            manifestPlaceholders["appAuthRedirectScheme"] = "com.gyadam.googleoauthtest"
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
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.accompanist.flowlayout)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Coroutine Lifecycle Scopes
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx.v231)

    implementation(libs.play.services.auth)
    //Dagger - Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.annotation.processor)
    implementation(libs.hilt.viewmodel)
    kapt(libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

}