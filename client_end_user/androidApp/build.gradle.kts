plugins {
    kotlin("multiplatform")
    id("com.android.application")
    id("org.jetbrains.compose")
}

kotlin {
    androidTarget()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":client_end_user:shared"))
            }
        }
    }
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    namespace = "org.thechance.beepbeep"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("../../design_system/shared/src/commonMain/resources")

    defaultConfig {
        applicationId = "org.thechance.beepbeep.enduser"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(libs.versions.jvmToolchain.get().toInt())
    }
}
dependencies{
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("androidx.paging:paging-runtime:3.3.0-alpha02")
    implementation("androidx.paging:paging-compose:3.3.0-alpha02")
    implementation("androidx.paging:paging-rxjava3:3.3.0-alpha02")
// etc.
}
