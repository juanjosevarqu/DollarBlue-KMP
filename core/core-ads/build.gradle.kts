import com.codingfeline.buildkonfig.compiler.FieldSpec.Type
import java.util.Properties
import kotlin.apply

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.android.lint)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.buildkonfig)
}

kotlin {
    androidLibrary {
        namespace = "com.varqulabs.dollarblue.core.ads"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    val xcfName = "CoreCoreAds"

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
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.koin.core)
            }
        }

        commonTest { dependencies { implementation(libs.kotlin.test) } }

        androidMain {
            dependencies {
                implementation(libs.play.services.ads)
            }
        }
    }
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

buildkonfig {
    packageName = "com.varqulabs.dollarblue.core.ads"
    defaultConfigs {
        buildConfigField(Type.STRING, "ADMOB_ID",      prop("ADMOB_ID"))
        buildConfigField(Type.STRING, "ADMOB_BANNER_ID",   prop("ADMOB_BANNER_ID"))
        buildConfigField(Type.STRING, "ADMOB_REWARDED_ID", prop("ADMOB_REWARDED_ID"))
    }
}