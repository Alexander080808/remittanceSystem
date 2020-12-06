package ru.otus.remittance.frontal

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import ru.otus.remittance.frontal.route.Router
import ru.otus.remittance.frontal.route.Router.Router
import zio.{Has, URManaged, ZIO, ZManaged}

import scala.concurrent.ExecutionContext

object App {

  def main(args: Array[String])  = {
    implicit val system: ActorSystem = ActorSystem("system")
    implicit val ex = system.dispatcher

    val bind = for {
      route   <- ZIO.service[Router.Service].map(_.route)
//      config  <- ZIO.service[HttpConfig]
      binding <- ZIO.fromFuture(_ => Http().newServerAt("127.0.0.1", 9000).bind(route)).orDie
    } yield binding

    val endpoint: URManaged[Router, Http.ServerBinding] =
      ZManaged.make(bind)(binding => ZIO.fromFuture((asdf: ExecutionContext) => binding.unbind()).orDie)

  }

}
