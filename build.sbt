
name := "remittanceSystem"

version := "0.1"

scalaVersion := "2.13.4"

val tapirVersion = "0.17.0-M8"

val zio = "1.0.1"

val zioConfig = "1.0.0-RC27"

val zioLogging = "0.5.2"

libraryDependencies ++= Seq(

  "com.softwaremill.sttp.tapir" %% "tapir-core" % tapirVersion,
  "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % tapirVersion,
  "com.softwaremill.sttp.tapir" %% "tapir-akka-http-server" % tapirVersion,
  "com.softwaremill.sttp.tapir" %% "tapir-openapi-docs" % tapirVersion,
  "com.softwaremill.sttp.tapir" %% "tapir-openapi-circe-yaml" % tapirVersion,
  "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-akka-http" % tapirVersion,
  "com.softwaremill.sttp.tapir" %% "tapir-redoc-akka-http" % tapirVersion,

  "dev.zio" %% "zio"                 % zio,
  "dev.zio" %% "zio-macros"          % zio,
  "dev.zio" %% "zio-config"          % zioConfig,
  "dev.zio" %% "zio-config-magnolia" % zioConfig,
  "dev.zio" %% "zio-config-typesafe" % zioConfig,
  "dev.zio" %% "zio-test"            % zio % Test,
  "dev.zio" %% "zio-test-sbt"        % zio % Test,
  "dev.zio" %% "zio-test-magnolia"   % zio % Test,
  "dev.zio" %% "zio-logging"         % zioLogging,
  "dev.zio" %% "zio-logging-slf4j"   % zioLogging
)