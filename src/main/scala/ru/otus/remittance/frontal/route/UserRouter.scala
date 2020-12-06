package ru.otus.remittance.frontal.route

import akka.http.scaladsl.server.Route
import zio.{Has, ULayer, URLayer, ZLayer}
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
import sttp.tapir.json.circe._

import scala.concurrent.Future

object UserRouter {
  type UserRouter = Has[Service]


  trait Service extends BaseRouter

  val live: URLayer[Any, UserRouter] =
    ZLayer.fromFunction( _ =>
      new Service {
        override def route: Route = route
      }
    )


  val getBooks =
    endpoint
      .in(query[String]("str"))
      .errorOut(stringBody)
      .out(stringBody)

  val route: Route = getBooks.toRoute(logic =>
    Future.successful{
      Right(s"$logic")
    }
  )
}
