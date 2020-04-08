import sbtcrossproject.CrossPlugin.autoImport.{CrossType, crossProject}

name := "webapp-scalajs"

version := "0.1"

scalaVersion in ThisBuild := "2.13.1"

lazy val `root` = project
  .in(file("."))
  .aggregate(`backend`)

lazy val `backend` = project
  .in(file("./backend"))
  .settings(
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http" % "10.1.11",
      "ch.megard" %% "akka-http-cors" % "0.4.2",
      "com.typesafe.akka" %% "akka-stream" % "2.6.4"
    )
  )
  .dependsOn(shared.jvm)

lazy val frontend = project
  .in(file("./frontend"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.CommonJSModule)
    },
    scalaJSUseMainModuleInitializer := true,
    mainClass in Compile := Some("App"),
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "1.0.0",
      "io.circe" %%% "circe-core" % "0.13.0",
      "io.circe" %%% "circe-generic" % "0.13.0",
      "io.circe" %%% "circe-parser" % "0.13.0",
    )
  )
  .dependsOn(shared.js)

lazy val shared = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("./shared"))
  .settings(
    libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.11"
  )
