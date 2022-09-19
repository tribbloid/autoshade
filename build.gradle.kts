val vs = versions()

buildscript {
    repositories {
        // Add here whatever repositories you're already using
        mavenCentral()
    }
}

plugins {
//    base
    `java-library`
    `java-test-fixtures`

    scala

    idea

    id("com.github.johnrengelman.shadow") version "7.1.2"
}

allprojects {

    apply(plugin = "java-library")

    apply(plugin = "idea")

    group = vs.projectGroup
    version = vs.projectV

    repositories {
        mavenLocal()
        mavenCentral()
//        jcenter()
//        maven("https://dl.bintray.com/kotlin/kotlin-dev")
//        maven("https://scala-ci.typesafe.com/artifactory/scala-integration/") // scala SNAPSHOT
    }

    idea {

        module {

            excludeDirs = excludeDirs + files(

                "target",
                "out",

                ".idea",
                ".vscode",
                ".bloop",
                ".bsp",
                ".metals",
                "bin",

                ".ammonite",

                "logs"
            )
        }
    }
}

subprojects {

    apply(plugin = "java-test-fixtures")
    apply(plugin = "scala")

    apply(plugin = "com.github.johnrengelman.shadow")
//    apply(plugin = "ru.tinkoff.gradle.jarjar")

    task("dependencyTree") {

        dependsOn("dependencies", "htmlDependencyReport")
    }

    tasks {

//        htmlDependencyReport {
//
//            reports.html.outputLocation.set(File("build/reports/dependencyTree/htmlReport"))
//        }

        withType<ScalaCompile> {

            scalaCompileOptions.apply {

                loggingLevel = "verbose"

                val compilerOptions =

                    mutableListOf(

                        "-encoding", "UTF-8", "-unchecked", "-deprecation", "-feature",

                        "-language:higherKinds",

                        "-Xlint:poly-implicit-overload", "-Xlint:option-implicit",

                        "-g:line",
                    )

                additionalParameters = compilerOptions

                forkOptions.apply {

                    memoryInitialSize = "1g"
                    memoryMaximumSize = "4g"
                }
            }
        }
//
//        withType<AbstractArchiveTask> {
//
//            isPreserveFileTimestamps = false
//            isReproducibleFileOrder = true
//        }
    }

    java {
        withSourcesJar()
        withJavadocJar()
    }
}

idea {

    module {

        excludeDirs = excludeDirs + files(
            ".gradle",

            // apache spark
            "warehouse"
        )

        isDownloadJavadoc = true
        isDownloadSources = true
    }
}
