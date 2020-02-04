import sbt._
name:= """my-form"""
version:= "1.0"

lazy val myform = (project in file(".")).enablePlugins(PlayScala)

resolvers += Resolver.sonatypeRepo("snapshots")

scalaVersion := "2.12.0"

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

resolvers  += "Online Play Repository" at
  "https://repo.typesafe.com/typesafe/maven-releases/"

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"


libraryDependencies ++= Seq( ws ,ehcache , specs2 % Test , guice )

val reactiveMongoVer = "0.18.7-play27"

val baseUnits: String = "0.1.22"
libraryDependencies += "com.pauldijou" %% "jwt-core" % "4.1.0"
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % "test"
libraryDependencies += "org.awaitility" % "awaitility" % "3.0.0" % Test
//Async JDBC Drivers
libraryDependencies += "org.scalikejdbc" %% "scalikejdbc-async" % "0.11.0"
libraryDependencies += "com.github.mauricio" %% "postgresql-async" % "0.2.21"
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.8"
libraryDependencies += "org.scalikejdbc" %% "scalikejdbc-config" % "3.3.5"
libraryDependencies += "org.scalikejdbc" %% "scalikejdbc-test" % "3.3.5"
libraryDependencies += "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.7.1-scalikejdbc-3.3"
libraryDependencies += "commons-dbcp" % "commons-dbcp" % "1.4"
libraryDependencies += "commons-pool" % "commons-pool" % "1.6"
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.2.0"
libraryDependencies += "org.sisioh" %% "baseunits-scala" % baseUnits
libraryDependencies += specs2 % Test
libraryDependencies += "org.reactivemongo" %% "play2-reactivemongo" % reactiveMongoVer
libraryDependencies += "com.roundeights" % "hasher_2.12" % "1.2.0"
libraryDependencies += "com.lucidchart" % "xtract_2.12" % "v2.1.0"
libraryDependencies += "com.typesafe.play" % "play-json-joda_2.12" % "2.7.4"
//added for jwt authentication
libraryDependencies += "com.jason-goodwin" % "authentikat-jwt_2.12" % "0.4.5"
//// enable Play cache API (based on your Play version)
//libraryDependencies += play.sbt.PlayImport.cacheApi
//// include play-redis library
libraryDependencies +=   "com.github.karelcemus" %% "play-redis" % "2.5.0"
//libraryDependencies += "com.github.karelcemus" %% "play-redis" % "2.4.0"
import com.typesafe.sbt.packager.MappingsHelper.directory
import play.sbt.routes.RoutesKeys
RoutesKeys.routesImport += "play.modules.reactivemongo.PathBindables._"
mappings in Universal ++= directory(baseDirectory.value / "public")
//unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )
scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-Xfatal-warnings"
)