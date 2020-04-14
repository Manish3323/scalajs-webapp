import org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv
import sbtcrossproject.CrossPlugin.autoImport.{CrossType, crossProject}

inThisBuild(
  Seq(
    name := "webapp-scalajs",
    version := "0.1",
    scalaVersion := "2.13.1",
    scalacOptions ++= Seq("-Ymacro-annotations"),
    resolvers ++= Seq(Resolver.bintrayRepo("mausamy", "tmtyped"))
  )
)

lazy val `root` = project
  .in(file("."))
  .aggregate(`backend`, frontend, shared.js, shared.jvm)

lazy val `backend` = project
  .dependsOn(shared.jvm)
  .settings(
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http" % "10.1.11",
      "ch.megard" %% "akka-http-cors" % "0.4.2",
      "com.typesafe.akka" %% "akka-stream" % "2.6.4",
      "io.bullet" %% "borer-core" % "1.5.0",
      "io.bullet" %% "borer-compat-akka" % "1.5.0"
    )
  )

lazy val frontend = project
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(shared.js)
  .settings(
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies ++= Seq(
      "io.bullet" %%% "borer-core" % "1.5.0",
      "io.bullet" %%% "borer-derivation" % "1.5.0",
      "org.scalablytyped" %%% "react" % "16.9.34-bd5dcd",
      "org.scalablytyped" %%% "react-dom" % "16.9.6-c2d8f7",
      "org.scalablytyped" %%% "rxjs" % "6.5.5-38125a",
      "me.shadaj" %%% "slinky-hot" % "0.6.5",
      "org.scalatest" %%% "scalatest" % "3.1.1",
      "org.scala-js" %%% "scalajs-dom" % "1.0.0"
    ),
    jsEnv := new JSDOMNodeJSEnv(), // imp :: `npm install jsdom`
    scalaJSLinkerConfig in Test ~= {
      _.withModuleKind(ModuleKind.NoModule) // since scripts are required as input to testing library commonjsModule gives invalid input.
    },
    jsEnv in Test := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv() // this needs to be set which makes sure  `window` is available by jsdom
  )

lazy val shared = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("./shared"))
  .settings(
    libraryDependencies ++= Seq(
      "io.bullet" %% "borer-core" % "1.5.0",
      "io.bullet" %% "borer-derivation" % "1.5.0"
    )
  )
