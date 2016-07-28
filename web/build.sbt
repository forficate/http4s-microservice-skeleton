import Dependencies._

libraryDependencies ++= Seq(
  http4sArgonaut,
  http4sBlazeServer,
  http4sDsl,
  specs2           % "test",
  specs2Matchers   % "test",
  specs2ScalaCheck % "test",
  specs2Scalaz     % "test"
)

sourceDirectory in Compile := baseDirectory.value / "app"
sourceDirectory in Test := baseDirectory.value / "test"

scalaSource in Compile := baseDirectory.value / "app"
scalaSource in Test := baseDirectory.value / "test"

javaSource in Compile := baseDirectory.value / "app"
javaSource in Test := baseDirectory.value / "test"
