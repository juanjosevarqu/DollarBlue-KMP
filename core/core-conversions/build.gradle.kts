plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

kotlin {
    androidLibrary {
        experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true

        namespace = "com.varqulabs.dollarblue.core.conversions"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    val xcfName = "CoreCoreConversions"

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
                implementation(projects.core.corePreferences)

                implementation(compose.runtime)
                implementation(compose.components.resources)

                implementation(libs.koin.core)
                implementation(libs.bundles.room.libraries)
            }
        }

        commonTest { dependencies { implementation(libs.kotlin.test) } }
    }

}

room { schemaDirectory("$projectDir/schemas") }

dependencies {
    add("kspAndroid", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
    add("kspIosX64", libs.androidx.room.compiler)
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)
}

compose.resources {
    this.publicResClass = true
}
