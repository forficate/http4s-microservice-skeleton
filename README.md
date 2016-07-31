# http4s-microservice-skeleton
Skeleton [HTTP4s](http://http4s.org/) Scala microservice project

This project uses:

* [Argonaut](http://argonaut.io/) for JSON serialization / deserialization
* [HTTP4S](http://http4s.org/) for HTTP routing and BlazeHTTP server
* [Log4J2](http://logging.apache.org/log4j/2.x/) for fast, asynchronous, structured JSON logging
* [Scalaz](https://github.com/scalaz/scalaz) for useful types / core Scala extensions
* [ScalaCheck](https://www.scalacheck.org/) to provide property based testing
* [Specs2](https://etorreborre.github.io/specs2/) to provide expressive test suites

`build.sbt` is configured with a strict set of `scalac` settings to promote good code practices and prevent errors.

## Project structure
```
├── README.md
├── build.sbt
├── conf
│   └── log4j2.xml
├── project
│   ├── Dependencies.scala
│   ├── build.properties
│   ├── plugins.sbt
├── scalastyle-config.xml
├── src
│   ├── main
│   │   └── scala
│   │       └── com
│   │           └── example
│   │               └── mymicroservice
│   │                   ├── MicroserviceConfig.scala
│   │                   └── MyMiroserviceApp.scala
│   └── test
│       ├── scala
└── web
    ├── app
    │   └── service
    │       ├── HealthCheckService.scala
    │       └── NotFoundService.scala
    ├── build.sbt
    ├── test
```
The project is structured as a multi-module sbt build.

Using multi-modules allow to keep compilation times down with incremental compilation and also allow for clear module boundaries to be set.

`built.sbt` defines the main project configuration, the common settings and the sub projects. Currently we only have one subproject, web.

### Dependencies 
```
├── project
│   ├── Dependencies.scala
```
Dependencies are listed under `project/Dependencies.scala` and imported in to the separate modules. Defining the dependencies in a central location allows for easy version management across the project.

### Root project

```
├── src
│   ├── main
│   │   └── scala
```

The root project handles the bootstrapping of the app, starting up the web server. The root project should be very thin.

The configuration is defined using environment variables following the [12 factor app](http://12factor.net/config) pattern. `MicroserviceConfig.scala` handles loading of the config, follow the pattern in use and expand the `MicroserviceConfig` case class with any additional properties needed.

### Web project

Web defines the HTTP routing handlers. Again this project should be kept as thin as possible, it should take arguments from a request, validate then delegate to some service project then render the response.

Add additional modules in the `build.sbt` using the existing web project example.

### Structured JSON logging

Log4j2 is configured to be used as the default logger with the fast Async appender. A slf4j bridge is also on the class path for 3rd party libraries used in your project which may be using slf4j where you want to see the log output.

Log4j2 is used configured with the async appender to provide fast non blocking logging. Click [here] (https://logging.apache.org/log4j/log4j-2.2/manual/async.html#Performance) to see details on log4j2 async performance.

