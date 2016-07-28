import Dependencies._

import scalariform.formatter.preferences._

val main = "com.example.mymicroservice.MyMicroserviceApp"

lazy val commonSettings = scalariformSettings ++ Seq(
  organization := "com.example",
  version := "0.0.1-SNAPSHOT",
  scalaVersion := "2.11.8",
  scalacOptions ++= Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-language:existentials",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-unchecked",
    "-Xfatal-warnings",
    "-Xlint",
    "-Yno-adapted-args",
    "-Ywarn-dead-code",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard",
    "-Xfuture",
    "-Ywarn-unused-import",
    "-Yrangepos",
    "-target:jvm-1.8"
  ),
  resolvers ++= Seq(
    "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/",
    "tpolecat" at "http://dl.bintray.com/tpolecat/maven",
    "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"
  ),

  unmanagedSourceDirectories in Compile ~= { _.filter(_.exists) }, //Stops the creation of java / scala-2.11 dirs
  resourceDirectory in Compile := baseDirectory.value / "conf",
  target := baseDirectory.value / "target",

  ScalariformKeys.preferences := ScalariformKeys.preferences.value
     .setPreference(AlignSingleLineCaseStatements, true)
     .setPreference(SpacesAroundMultiImports, true)
     .setPreference(AlignArguments, true)
     .setPreference(AlignParameters, true)
)

lazy val root = (project in file("."))
  .settings(commonSettings: _*)
  .settings(Revolver.settings: _*)
  .settings(cancelable in Global := true) //Prevent CTRL-C exiting to the OS
  .settings(mainClass in assembly := Some(main))
  .settings(mainClass in reStart := Some(main))
  .dependsOn(web)
  .aggregate(web)

lazy val web = (project in file("web"))
  .settings(commonSettings: _*)
