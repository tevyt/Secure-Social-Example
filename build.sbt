name := """SecureSocialDemo2"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "ws.securesocial" %% "securesocial" % "master-SNAPSHOT",
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4"
)

resolvers += Resolver.sonatypeRepo("snapshots")
