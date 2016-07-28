package com.example.mymicroservice.service

import argonaut._, Argonaut._
import org.http4s._, org.http4s.dsl._, org.http4s.argonaut._

/*
 * Default 404 handler returning JSON in standard
 * format for clients to consume.
 *
 * key: is an immutable value and should be used for client error handling to match against aswell as the HTTP status code
 * message: is a human readable message
 */
object NotFoundService {
  private val errorJson =
    Json(
      "error" -> Json(
        "statusCode" := 404,
        "key" := "notfound",
        "message" := "The requested resource was not found"
      )
    )

  def apply() = HttpService.lift { _ => NotFound(errorJson) }
}
