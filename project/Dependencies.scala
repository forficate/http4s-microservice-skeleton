import sbt._

object Dependencies {
  val http4sVersion  = "0.14.1"
  val jacksonVersion = "2.8.1"
  val log4J2Version  = "2.6.2"
  val scalazVersion  = "7.2.4"
  val specs2Version  = "3.8.4"

  val http4sArgonaut    = "org.http4s"                  %% "http4s-argonaut"      % http4sVersion
  val http4sBlazeServer = "org.http4s"                  %% "http4s-blaze-server"  % http4sVersion
  val http4sDsl         = "org.http4s"                  %% "http4s-dsl"           % http4sVersion
  val jacksonCore       = "com.fasterxml.jackson.core"   % "jackson-core"         % jacksonVersion
  val jacksonDatabind   = "com.fasterxml.jackson.core"   % "jackson-databind"     % jacksonVersion
  val log4J2Api         = "org.apache.logging.log4j"     % "log4j-api"            % log4J2Version
  val log4J2Core        = "org.apache.logging.log4j"    % "log4j-core"            % log4J2Version
  val log4J2Slf4J       = "org.apache.logging.log4j"    % "log4j-slf4j-impl"      % log4J2Version
  val scalazConcurrent  = "org.scalaz"                  %% "scalaz-concurrent"    % scalazVersion
  val scalazCore        = "org.scalaz"                  %% "scalaz-core"          % scalazVersion
  val scalazEffect      = "org.scalaz"                  %% "scalaz-effect"        % scalazVersion
  val specs2            = "org.specs2"                  %% "specs2-core"          % specs2Version
  val specs2Matchers    = "org.specs2"                  %% "specs2-matcher-extra" % specs2Version
  val specs2ScalaCheck  = "org.specs2"                  %% "specs2-scalacheck"    % specs2Version
  val specs2Scalaz      = "org.typelevel"               %% "scalaz-specs2"        % "0.3.0"
}
