package com.example.mymicroservice

import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicReference
import org.http4s.server.Server
import scala.annotation.tailrec
import org.http4s.server.blaze.BlazeBuilder
import com.example.mymicroservice.service._
import org.apache.logging.log4j.LogManager
import scalaz.concurrent.Task
import scalaz.{ \/, -\/, \/- }

final case class MyMicroserviceApp(config: MicroserviceConfig) {

  def run: Task[Server] =
    BlazeBuilder
      .bindHttp(port = config.port.value)
      .mountService(HealthCheckService(), "/healthcheck")
      .mountService(NotFoundService())
      .start

}

object MyMicroserviceApp {
  private val log = LogManager.getLogger(classOf[MyMicroserviceApp])

  /**
   * Return a task to shutdown the application.
   *
   *  This task is run as a JVM shutdown hook
   *
   *  The default implementation shuts down the server, and waits for
   *  it to finish.  Other resources may shutdown by flatMapping this
   *  task.
   */
  private def shutdown(server: Server): Task[Unit] =
    server.shutdown

  private sealed trait LifeCycle
  private case object Init extends LifeCycle
  private case object Started extends LifeCycle
  private case object Stopping extends LifeCycle
  private case object Stopped extends LifeCycle

  /** The current state of the server. */
  private val state =
    new AtomicReference[LifeCycle](Init)

  @tailrec
  private def doShutdown(s: Server): Unit =
    state.get match {
      case _ if (state.compareAndSet(Started, Stopping)) =>
        log.info(s"Shutting down server on ${s.address}")
        try shutdown(s).run
        finally state.set(Stopped)
        log.info(s"Stopped server on ${s.address}")
      case Stopping | Stopped =>
        ()
      case state =>
        log.info(s"Tried to shutdown server $s, but was in state $state.  Trying again in 1 second")
        Thread.sleep(1000)
        doShutdown(s)
    }

  final def main(args: Array[String]): Unit = {
    val app: String \/ MyMicroserviceApp = MicroserviceConfig.fromEnv.map(MyMicroserviceApp.apply)

    app match {
      case -\/(error) => {
        log.error(error)
        sys.exit(1)
      }
      case \/-(app) => {
        val s = app.run.map { s =>
          sys.addShutdownHook { doShutdown(s) }
          s
        }.run
        state.set(Started)
        log.info(s"Server started at ${s.address}")
        new CountDownLatch(1).await
      }
    }

  }

}
