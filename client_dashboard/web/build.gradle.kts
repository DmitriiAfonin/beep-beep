plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "org.thechance"
version = "1.0-SNAPSHOT"

kotlin {
    js(IR) {
        browser {
            commonWebpackConfig {
                outputFileName = "particles-app.js"
            }
        }
        binaries.executable()
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
//                implementation(compose.runtime)
//                implementation(compose.foundation)
//                implementation(compose.material)
                implementation(project(":client_dashboard:common"))
//                implementation(project(":design_system:shared"))
//                implementation("io.github.thechance101:chart:Beta-0.0.5")
            }
        }
    }
}

compose.experimental {
    web.application {}
}

compose {
    kotlinCompilerPlugin.set("1.5.0")
    kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=1.9.10")
}