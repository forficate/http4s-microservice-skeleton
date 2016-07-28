package com.example.mymicroservice.service

import org.http4s._, org.http4s.dsl._

/*
 * A service which returns 200 for all requests.
 *
 * This is designed to be mounted as an healthcheck for
 * AWS ELBs / load balancers / reverse proxies to indicate
 * the application is running by returning a 200 status.
 *
 * Example useage:
 * {{{
 * BlazeBuilder.mountService(HealthCheck(), "/healthcheck")
 * }}}
 */
object HealthCheckService {
  def apply() = HttpService.lift { _ => Ok() }
}
