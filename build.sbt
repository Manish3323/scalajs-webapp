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

val testDev = Def.taskKey[Unit]("test in dev mode")
val testProd = Def.taskKey[Unit]("test in prod mode")

def runJest(): Unit = {
  import sys.process._

  val jestResult = """npm --prefix frontend run test""".!
  if (jestResult != 0)
    throw new IllegalStateException("Jest Suite failed")
}

lazy val `root` = project
  .in(file("."))
  .aggregate(`backend`, frontend, jest, shared.js, shared.jvm)

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

lazy val frontend =
  project
    .enablePlugins(ScalaJSPlugin)
    .dependsOn(shared.js, jest % Test)
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
        "com.raquo" %%% "laminar" % "0.9.0",
        "com.raquo" %%% "domtestutils" % "0.12.0" % Test
      ),
      Test / jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),
      Test / scalaJSUseTestModuleInitializer := false,
      Test / scalaJSUseMainModuleInitializer := true,
      Test / fastOptJS / artifactPath :=
        ((target in fastOptJS).value /
          ((moduleName in fastOptJS).value + "-fastopt.test.js")),
      Test / fullOptJS / artifactPath :=
        ((target in fullOptJS).value /
          ((moduleName in fullOptJS).value + "-opt.test.js")),
      testDev := {
        (fastOptJS in Test).value
        runJest()
      },
      testProd := {
        (fullOptJS in Test).value
        runJest()
      }
    )

lazy val jest =
  project
    .enablePlugins(ScalaJSPlugin)
    .disablePlugins(RevolverPlugin)
    .settings(
      libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.13.1",
      scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) }
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
