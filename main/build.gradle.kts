
val vs = versions()

dependencies {

    api("${vs.scalaGroup}:scala-compiler:${vs.scalaV}")
    api("${vs.scalaGroup}:scala-library:${vs.scalaV}")
    api("${vs.scalaGroup}:scala-reflect:${vs.scalaV}")

    api(project(":repack:gradle", configuration = "shadow"))

    api("com.tribbloids.autoshade:repack-maven:0.0.1-SNAPSHOT")
    api("com.tribbloids.autoshade:repack-sbt_${vs.scalaBinaryV}:0.0.1-SNAPSHOT:assembly")
}
