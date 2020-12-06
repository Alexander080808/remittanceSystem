package ru.otus.remittance.frontal.route

import akka.http.scaladsl.server.Directives.{concat, pathPrefix}
import akka.http.scaladsl.server.Route
import sttp.tapir.swagger.akkahttp.SwaggerAkka
import zio.{Has, URLayer, ZLayer}
import akka.http.scaladsl.server.Directives._
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import ru.otus.remittance.frontal.route.UserRouter.UserRouter
import sttp.tapir.server.akkahttp.RichAkkaHttpEndpoint
import sttp.tapir.{endpoint, query, stringBody}
import sttp.tapir.docs.openapi._
import sttp.tapir.openapi.circe.yaml._
import sttp.tapir.swagger.akkahttp.SwaggerAkka
import akka.http.scaladsl.server.Directives._
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import sttp.tapir.server.akkahttp.RichAkkaHttpEndpoint
import sttp.tapir.{endpoint, query, stringBody}
import sttp.tapir.docs.openapi._
import sttp.tapir.openapi.circe.yaml._
import sttp.tapir.swagger.akkahttp.SwaggerAkka
import sttp.tapir.json.circe._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

import scala.concurrent.Future

object Router {

  type Router = Has[Service]

  trait Service extends BaseRouter

  val live: URLayer[UserRouter, Router] = ZLayer.fromFunction { env =>
    val userRouter = env.get[UserRouter.Service]

    new Service {
      def route: Route = {
        val routes = pathPrefix("api" / "v1") {
          concat(
            userRouter.route
          )
        }

        val docs = List(UserRouter.getBooks).toOpenAPI("asdf", "v1")
        val yaml = docs.toYaml

        routes ~ new SwaggerAkka(yaml).routes
      }
    }
  }
}
