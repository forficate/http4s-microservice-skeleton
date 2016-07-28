package com.example.mymicroservice

import org.http4s.server.Server
import org.http4s.server.ServerApp
import org.http4s.server.blaze.BlazeBuilder
import com.example.mymicroservice.controller._
import scalaz.concurrent.Task

final case class MyMiroserviceApp(port: Int) {

  def run: Task[Server] =
    BlazeBuilder
      .bindHttp(port = port)
      .mountService(HealthCheck(), "/healthcheck")
      .start

}

object MyMiroserviceApp extends ServerApp {
  override def server(args: List[String]) = MyMiroserviceApp(8080).run
}
