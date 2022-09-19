
//include("graph-commons")
//project(":graph-commons").projectDir = file("graph-commons/core") TODO: enable later

include(
    // should be skipped on CI, contains local experiments only
    ":repack:gradle",
    ":main"
)

pluginManagement.repositories {
    gradlePluginPortal()
    mavenCentral()
    // maven("https://dl.bintray.com/kotlin/kotlin-dev")
}
