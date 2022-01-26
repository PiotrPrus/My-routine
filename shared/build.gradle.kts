plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("com.squareup.sqldelight")
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    macosX64()
    macosArm64()

    sourceSets {
        /* Main source sets */
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
                implementation("co.touchlab:kermit:1.0.0")
                implementation("io.ktor:ktor-client-core:2.0.0-beta-1")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:2.0.0-beta-1")
                implementation("com.squareup.sqldelight:android-driver:1.5.3")
            }
        }
        val iosX64Main by getting 
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val macosX64Main by getting 
        val macosArm64Main by getting
        val iosMain by creating {
            dependencies {
                implementation("io.ktor:ktor-client-ios:2.0.0-beta-1")
            }
        }
        val macosMain by creating
        val nativeMain by creating {
            dependencies {
                implementation("com.squareup.sqldelight:native-driver:1.5.3")
            }
        }

        /* Main hierarchy */
        androidMain.dependsOn(commonMain)
        iosMain.dependsOn(nativeMain)
        iosX64Main.dependsOn(iosMain)
        iosArm64Main.dependsOn(iosMain)
        iosSimulatorArm64Main.dependsOn(iosMain)
        macosMain.dependsOn(nativeMain)
        macosX64Main.dependsOn(macosMain)
        macosArm64Main.dependsOn(macosMain)
        nativeMain.dependsOn(commonMain)

        /* Test source sets */
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidTest by getting
        val iosX64Test by getting 
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val macosX64Test by getting 
        val macosArm64Test by getting
        val iosTest by creating
        val macosTest by creating
        val nativeTest by creating

        /* Test hierarchy */
        androidTest.dependsOn(commonTest)
        iosTest.dependsOn(nativeTest)
        iosX64Test.dependsOn(iosTest)
        iosArm64Test.dependsOn(iosTest)
        iosSimulatorArm64Test.dependsOn(iosTest)
        macosTest.dependsOn(nativeTest)
        macosX64Test.dependsOn(macosTest)
        macosArm64Test.dependsOn(macosTest)
        nativeTest.dependsOn(commonTest)
    }
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 31
    }
}
