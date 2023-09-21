@file:Suppress("DSL_SCOPE_VIOLATION")
group = "org.thechance"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        mavenLocal()
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
    }
}

plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.compose) apply false
}



configurations.all {
    val conf = this
    // Currently it's necessary to make the android build work properly
    conf.resolutionStrategy.eachDependency {
        val isWasm = conf.name.contains("wasm", true)
        val isJs = conf.name.contains("js", true)
        val isComposeGroup = requested.module.group.startsWith("org.jetbrains.compose")
        val isComposeCompiler = requested.module.group.startsWith("org.jetbrains.compose.compiler")
        if (isComposeGroup && !isComposeCompiler && !isWasm && !isJs) {
            val composeVersion = project.property("compose.version") as String
            useVersion(composeVersion)
        }
        if (requested.module.name.startsWith("kotlin-stdlib")) {
            val kotlinVersion = project.property("kotlin.version") as String
            useVersion(kotlinVersion)
        }
    }
}