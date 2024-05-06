import org.jetbrains.kotlin.konan.properties.Properties


plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hiltAndroid)
}

val usercentricsFile = project.rootProject.file("usercentrics.properties")
val usercentricsproperties = Properties()
usercentricsproperties.load(usercentricsFile.inputStream())

android {
    namespace = "com.yemyatthu.usercentricsappchallenge"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.yemyatthu.usercentricsappchallenge"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        val usercentricsSettingsId = usercentricsproperties.getProperty("settingId")?:""
        buildConfigField(type = "String", name = "USERCENTRICS_SETTINGS_ID", value = usercentricsSettingsId)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.startup)
    implementation(libs.material)
    implementation(libs.usercentrics.sdk)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.hiltAndroid)
    ksp(libs.hiltAndroidCompiler)
    testImplementation(libs.junit)
    testImplementation(libs.mockito)
    testImplementation(libs.kotlin.coroutines.test)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}