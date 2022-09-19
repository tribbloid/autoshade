val vs = versions()

configurations.all {

    exclude("org.scala-lang","scala-library")
//
//    resolutionStrategy {
//
////            force("org.scala-lang.modules:scala-xml_${vs.scalaBinaryV}:1.0.6")
////            force("commons-codec:commons-codec:1.9")
////            force("org.json4s:json4s-jackson_${vs.scalaBinaryV}:3.5.5")
//    }
}

dependencies {

    api("org.json4s:json4s-jackson_${vs.scalaBinaryV}:4.0.4")
}

tasks {
    shadowJar {
        exclude("META-INF/*.SF")
        exclude("META-INF/*.DSA")
//        exclude("META-INF/*.RSA")

        relocate("org.json4s", "repacked.test2.org.json4s")
    }


//    withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
//        //...//
//        finalizedBy(jarJar)
//    }
}
