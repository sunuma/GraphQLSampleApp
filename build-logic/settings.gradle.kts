/**
 *
 * https://github.com/android/nowinandroid/blob/main/build-logic/settings.gradle.kts
 */
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "build-logic"
include(":convention")