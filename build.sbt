ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.5.1"

lazy val root = (project in file("."))
  .settings(
    name := "streaming-problem",
    idePackagePrefix := Some("streaming"),
    libraryDependencies ++= Seq(
      "co.fs2"        %% "fs2-core"                   % "3.10.2",
      "org.typelevel" %% "cats-effect"                % "3.5.4"
    )
  )
