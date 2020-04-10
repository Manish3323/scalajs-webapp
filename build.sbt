import sbtcrossproject.CrossPlugin.autoImport.{CrossType, crossProject}

name := "webapp-scalajs"

version := "0.1"

scalaVersion in ThisBuild := "2.13.1"

scalacOptions in ThisBuild ++= Seq("-Ymacro-annotations")

resolvers in ThisBuild ++= Seq(Resolver.bintrayRepo("mausamy", "tmtyped"))

lazy val `root` = project
  .in(file("."))
  .aggregate(`backend`)

lazy val `backend` = project
  .in(file("./backend"))
  .settings(
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http" % "10.1.11",
      "ch.megard" %% "akka-http-cors" % "0.4.2",
      "com.typesafe.akka" %% "akka-stream" % "2.6.4",
      "io.bullet" %% "borer-core" % "1.5.0",
      "io.bullet" %% "borer-compat-akka" % "1.5.0"
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
    libraryDependencies ++= Seq(
      "io.bullet" %%% "borer-core" % "1.5.0",
      "io.bullet" %%% "borer-derivation" % "1.5.0",
      "org.scalablytyped" %%% "react" % "16.9.34-bd5dcd",
      "org.scalablytyped" %%% "react-dom" % "16.9.6-c2d8f7",
      "me.shadaj" %%% "slinky-hot" % "0.6.4+2-3c8aef65"
    )
  )
  .dependsOn(shared.js)

lazy val shared = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("./shared"))
  .settings(
    libraryDependencies ++= Seq(
      "io.bullet" %% "borer-core" % "1.5.0",
      "io.bullet" %% "borer-derivation" % "1.5.0"
    )
  )
