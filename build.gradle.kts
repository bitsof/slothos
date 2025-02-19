// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.8.0" apply false
    id ("com.android.library") version "8.8.0" apply false
    id("org.jetbrains.kotlin.android") version "2.1.10" apply false
    id("com.google.devtools.ksp") version "2.1.10-1.0.29" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.21" apply false
    alias(libs.plugins.compose.compiler) apply false
}