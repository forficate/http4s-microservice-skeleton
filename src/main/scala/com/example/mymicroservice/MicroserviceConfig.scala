package com.example.mymicroservice

import scalaz.\/
import scalaz._, Scalaz._

final case class MicroserviceConfig(port: MicroserviceConfig.Port)

object MicroserviceConfig {
  final case class Port(value: Integer) extends AnyVal

  def fromEnv: String \/ MicroserviceConfig =
    port map MicroserviceConfig.apply

  private def port: String \/ Port =
    for {
      envVar <- requiredEnvVar("PORT")
      port <- \/.fromTryCatchNonFatal(Integer.valueOf(envVar)).leftMap(_ => "PORT is not numeric")
    } yield Port(port)

  private def requiredEnvVar(envVar: String): String \/ String =
    sys.env.get(envVar).toRightDisjunction(s"Required ${envVar} environment variable is missing")

}
