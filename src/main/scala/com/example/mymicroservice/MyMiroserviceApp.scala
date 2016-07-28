package com.example.mymicroservice

import org.http4s.server.Server
import org.http4s.server.ServerApp
import org.http4s.server.blaze.BlazeBuilder
import com.example.mymicroservice.service._
import scalaz.concurrent.Task

final case class MyMicroserviceApp(port: Int) {

  def run: Task[Server] =
    BlazeBuilder
      .bindHttp(port = port)
      .mountService(HealthCheckService(), "/healthcheck")
      .mountService(NotFoundService())
      .start

}

object MyMicroserviceApp extends ServerApp {
  override def server(args: List[String]) = MyMicroserviceApp(8080).run
}
