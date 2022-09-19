import org.gradle.api.Project

class Versions(self: Project) {

    val projectGroup = "com.tribbloids.autoshade"
    val projectRootID = "autoshade"

    val projectVMajor = "0.0.1"
    val projectV = "${projectVMajor}-SNAPSHOT"

    val scalaGroup: String = "org.scala-lang"
    val scalaV: String = self.properties.get("scalaVersion").toString()

    protected val scalaVParts = scalaV.split('.')

    val scalaBinaryV: String = scalaVParts.subList(0, 2).joinToString(".")
    val scalaMinorV: String = scalaVParts[2]
}
