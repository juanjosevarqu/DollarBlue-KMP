plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.android.lint)
}

kotlin {
    androidLibrary {
        namespace = "com.varqulabs.dollarblue.core.preferences"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    val xcfName = "CoreCorePreferences"

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
                implementation(libs.koin.core)
                api(libs.bundles.datastore.libraries)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}