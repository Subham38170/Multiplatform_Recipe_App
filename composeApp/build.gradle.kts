import org.gradle.declarative.dsl.schema.FqName.Empty.packageName
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)

    id("app.cash.sqldelight") version "2.2.1"


}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    jvm()
    
    js {
        browser()
        binaries.executable()
    }

    
    sourceSets {

        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.activity.compose)


            //SQLDelight
            implementation(libs.android.driver)

            //Ktor
            implementation(libs.ktor.client.okhttp)


        }
        iosMain.dependencies {
            //SQLDelight
            implementation(libs.native.driver)


            //Ktor
            implementation(libs.ktor.client.darwin)

        }
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            //Ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.logging)

            //Adaptive Navigation Suite
            implementation(libs.material3.adaptive.navigation.suite)
            implementation(libs.material3.adaptive)
            implementation(libs.material.icons.extended)

            // Navigation 3
            implementation(libs.androidx.navigation3.runtime)


            //Coil
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor3)


            //Koin
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.compose.viewmodel.navigation)



            //Datetime
            implementation(libs.kotlinx.datetime) // Check for the latest version

            //Setting
            implementation(libs.multiplatform.settings) // Check


            //Adapter
            implementation(libs.primitive.adapters)

        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
            //SQLDelight
            implementation(libs.sqlite.driver)

            //Ktor
            implementation(libs.ktor.client.java) // or ktor-client-apache, ktor-client-jetty


        }
        jsMain.dependencies {
            //SQLDelight
            implementation(libs.web.worker.driver)
            implementation(devNpm("copy-webpack-plugin", "9.1.0"))
            //Ktor
            implementation(libs.ktor.client.js)

        }
    }
}

android {
    namespace = "org.subham.recipeapp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.subham.recipeapp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(libs.compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "org.subham.recipeapp.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.subham.recipeapp"
            packageVersion = "1.0.0"
        }
    }
}


sqldelight {
    databases {
        create("RecipeAppDb") {
            packageName = "org.subham.recipeapp"
            generateAsync = true
        }
    }
}