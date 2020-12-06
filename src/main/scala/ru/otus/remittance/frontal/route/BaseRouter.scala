package ru.otus.remittance.frontal.route

import akka.http.scaladsl.server.Route

trait BaseRouter {
  def route: Route
}
