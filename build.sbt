import Dependencies._

import scalariform.formatter.preferences._

val mainClass_ = "com.example.mymicroservice.MyMicroserviceApp"

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
    "-target:jvm-1.8",
    "-optimise"
  ),

  resolvers ++= Seq(
    Resolver.typesafeRepo("releases"),
    "tpolecat" at "http://dl.bintray.com/tpolecat/maven",
    "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"
  ),

  unmanagedSourceDirectories in Compile ~= { _.filter(_.exists) }, //Stops the creation of java / scala-2.11 dirs
  resourceDirectory in Compile := baseDirectory.value / "conf",
  target := baseDirectory.value / "target",

  libraryDependencies ++= Seq (
    log4J2Api /* All subprojects should use log4J2 API */
  ),

  ScalariformKeys.preferences := ScalariformKeys.preferences.value
     .setPreference(AlignSingleLineCaseStatements, true)
     .setPreference(SpacesAroundMultiImports, true)
     .setPreference(AlignArguments, true)
     .setPreference(AlignParameters, true)
)

lazy val root = (project in file("."))
  .settings(commonSettings: _*)
  .settings(Revolver.settings: _*)
  .settings(Seq(assembly, reStart).map(mainClass in _ := Some(mainClass_)))
  .settings(libraryDependencies ++= Seq( /* Logging dependencies */
    log4J2Core,
    log4J2Slf4J, /* log4j2 bridge to get log messages from libs using slf4j */
    jacksonCore,
    jacksonDatabind
  ))
  .dependsOn(web)
  .aggregate(web)

lazy val web = (project in file("web"))
  .settings(commonSettings: _*)
