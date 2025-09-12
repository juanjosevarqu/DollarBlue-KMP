plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.android.lint)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {

    androidLibrary {
        namespace = "com.varqulabs.dollarblue.feature.welcome"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    val xcfName = "FeatureWelcome"

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
        commonMain {
            dependencies {
                implementation(projects.core.coreUi)
                implementation(projects.core.coreCommon)
                implementation(projects.core.coreDesignsystem)
                implementation(projects.core.corePreferences)

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.uiToolingPreview)

                implementation(libs.androidx.navigation.compose)
                implementation(libs.bundles.koin.libraries)
            }
        }

        commonTest { dependencies { implementation(libs.kotlin.test) } }

        androidMain { dependencies {} }

        iosMain { dependencies {} }
    }

}