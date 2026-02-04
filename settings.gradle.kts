pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "NewsApi"
// app
include(":app")

// core
include(":core:common")
include(":core:network")
include(":core:database")
include(":core:ui")

// features:news
include(":feature:news:data")
include(":feature:news:domain")
include(":feature:news:presentation")
