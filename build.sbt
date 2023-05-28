ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.0"

lazy val root = (project in file("."))
  .settings(
    name := "dota-gsi",
    libraryDependencies ++= Seq(
      "com.github.mrbean355" % "dota2-gsi" % "2.3.0",
      "com.softwaremill.sttp.client3" %% "core" % "3.8.15",
      "com.lihaoyi" %% "os-lib" % "0.9.1"
    )
  )
