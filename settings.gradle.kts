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

rootProject.name = "Tickets"
include(":app")
include(":core")
include(":core:network")
include(":core:common")
include(":core:model")
include(":core:data")
include(":core:domain")
include(":feature")
include(":core:designsystem")
include(":feature:tickets")
include(":feature:hotels")
include(":feature:short")
include(":feature:subscriptions")
include(":feature:profile")
