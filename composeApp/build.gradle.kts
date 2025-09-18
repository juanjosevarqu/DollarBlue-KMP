import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.ksp)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
            binaryOption("bundleId", "com.varqulabs.dollarblue.DollarBlueKMP") // Warning Build IOS
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.coreUi)
                implementation(projects.core.coreCommon)
                implementation(projects.core.corePreferences)
                implementation(projects.core.coreConversions)
                implementation(projects.core.coreCredits)
                implementation(projects.core.coreAds)
                implementation(projects.feature.welcome)
                implementation(projects.feature.calculator)
                implementation(projects.feature.history)

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)

                implementation(libs.androidx.navigation.compose)
                implementation(libs.bundles.koin.libraries)
            }
        }

        commonTest { dependencies { implementation(libs.kotlin.test) } }

        androidMain {
            dependencies {
                implementation(libs.androidx.core.splashscreen)
                implementation(libs.bundles.glance.libraries)
            }
        }
    }
}

dependencies {
    add("kspAndroid", libs.koin.ksp.compiler)
    add("kspIosArm64", libs.koin.ksp.compiler)
    add("kspIosX64", libs.koin.ksp.compiler)
    add("kspIosSimulatorArm64", libs.koin.ksp.compiler)
}

ksp {
    arg("KOIN_CONFIG_CHECK", "true")
    arg("KOIN_DEFAULT_MODULES", "false")
}

val localProps: Properties by lazy {
    Properties().apply {
        val candidates = listOf(
            rootProject.file("local.properties"),
            project.file("local.properties")
        )
        for (f in candidates) if (f.exists()) f.inputStream().use(::load)
    }
}

fun prop(key: String): String =
    localProps.getProperty(key)
        ?: providers.environmentVariable(key).orNull
        ?: error("Falta '$key' en local.properties o variables de entorno")

android {
    namespace = "com.varqulabs.dollarblue"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.varqulabs.dolarblueapp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        manifestPlaceholders["ADMOB_ID"] = prop("ADMOB_ID")
        manifestPlaceholders["ADMOB_BANNER_ID"] = prop("ADMOB_BANNER_ID")
        manifestPlaceholders["ADMOB_REWARDED_ID"] = prop("ADMOB_REWARDED_ID")
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
}

dependencies {
    debugImplementation(compose.uiTooling)
}
