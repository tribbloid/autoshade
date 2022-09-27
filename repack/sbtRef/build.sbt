// automatically reload the build when source changes are detected
Global / onChangedBuildSource := ReloadOnSourceChanges

lazy val commonSettings = Seq(
  organization := "com.tribbloids.autoshade",
  name := "repack-sbtRef",
  version := "0.0.1-SNAPSHOT",
  scalaVersion := "2.13.9"
//  test in assembly := {},
  // If you are using a maven repository
  // https://www.scala-sbt.org/1.x/docs/Publishing.html
//  publishMavenStyle := true,
)

//val disablePublishing = Seq[Setting[_]](
//  publishArtifact := false,
//  // The above is enough for Maven repos but it doesn't prevent publishing of ivy.xml files
//  publish := {},
//  publishLocal := {},
//)

lazy val root = project
  .in(file("."))
  .settings(commonSettings)
  .settings(
    scalacOptions += "-Ymacro-annotations",
    libraryDependencies ++= Seq(
      "org.json4s" %% "json4s-jackson" % "4.0.4"
    ),
    addArtifact(
      Artifact("repack-sbtRef", "assembly"),
      sbtassembly.AssemblyKeys.assembly
    ),
    ThisBuild / assemblyMergeStrategy := {
      case PathList("module-info.class")         => MergeStrategy.discard
      case x if x.endsWith("/module-info.class") => MergeStrategy.discard
      case x =>
        val oldStrategy = (ThisBuild / assemblyMergeStrategy).value
        oldStrategy(x)
    },
    artifact in (Compile, assembly) := {
      val art = (artifact in (Compile, assembly)).value
      art.withClassifier(Some("assembly"))
    },
    ThisBuild / assemblyJarName := {
      s"${name.value}-${scalaBinaryVersion.value}-${version.value}-assembly.jar"
    },
    ThisBuild / assemblyShadeRules := Seq(
      ShadeRule.rename("org.json4s.**" -> "repacked.test1.org.json4s.@1").inAll
    )
  )
  .enablePlugins(AssemblyPlugin)
