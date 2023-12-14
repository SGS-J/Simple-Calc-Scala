ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

libraryDependencies += "org.scalafx" %% "scalafx" % "21.0.0-R32"
enablePlugins(JavaAppPackaging)

lazy val root = (project in file("."))
  .settings(
    name := "Calculator-In-Scala",
    idePackagePrefix := Some("org.SGSJ.calc"),
    Compile / mainClass := Some("org.SGSJ.calc.Main")
  )
