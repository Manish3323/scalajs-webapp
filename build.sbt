import sbtcrossproject.CrossPlugin.autoImport.{CrossType, crossProject}

name := "webapp-scalajs"

version := "0.1"

scalaVersion in ThisBuild := Settings.versions.scala

scalacOptions in ThisBuild ++= Settings.scalacOptions

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

lazy val frontend =
  (crossProject(JSPlatform).crossType(CrossType.Pure) in file("./frontend"))
    .enablePlugins(ScalaJSPlugin)
    .jsSettings(
      scalaJSLinkerConfig ~= {
        _.withModuleKind(ModuleKind.CommonJSModule)
      },
      scalaJSUseMainModuleInitializer := true,
      mainClass in Compile := Some("App"),
      libraryDependencies ++= Seq(
        "org.scala-js" %%% "scalajs-dom" % "1.0.0",
        "com.lihaoyi" %%% "autowire" % "0.2.6"
      )
    )
