plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.android.lint)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {

    androidLibrary {
        experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true

        namespace = "com.varqulabs.dollarblue.feature.calculator"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        withHostTestBuilder {}
        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    val xcfName = "FeatureCalculator"

    iosX64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosSimulatorArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    sourceSets {
        sourceSets.all {
            languageSettings.optIn("kotlin.time.ExperimentalTime")
        }

        commonMain {
            dependencies {
                implementation(projects.core.coreUi)
                implementation(projects.core.coreCommon)
                implementation(projects.core.coreDesignsystem)
                implementation(projects.core.coreNetwork)
                implementation(projects.core.corePreferences)
                implementation(projects.core.coreConversions)
                implementation(projects.core.coreCredits)
                implementation(projects.core.coreAds)

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

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.kotlinx.coroutines.test)
                implementation(libs.turbine)

                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.uiTest)
            }
        }

        androidMain { dependencies {} }

        iosMain { dependencies {} }

        getByName("androidDeviceTest") {
            dependencies {
                implementation(libs.androidx.runner)
                implementation(libs.androidx.core)
                implementation(libs.androidx.junit)
                implementation(libs.androidx.room.runtime)
            }
        }

        getByName("androidHostTest") {
            dependencies {
                implementation(libs.androidx.runner)
                implementation(libs.androidx.core)
                implementation(libs.androidx.junit)
                implementation(libs.androidx.room.runtime)
            }
        }
    }

}