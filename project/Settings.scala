import sbt._

object Settings {

  object versions {
    val scala = "2.13.1"
    val akka = "2.6.4"
    val `akka-http` = "10.1.11"
    val `akka-http-json` = "1.31.0"
    val `typesafe-config` = "1.4.0"
  }

  val scalacOptions = Seq(
    "-target:jvm-1.8",
    "-unchecked",
    "-deprecation",
    "-feature",
    "-Xlint:nullary-unit,inaccessible,nullary-override,infer-any,missing-interpolator,private-shadow,type-parameter-shadow,poly-implicit-overload,option-implicit,delayedinit-select,stars-align",
    "-Xcheckinit",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-Ywarn-value-discard",
    // for Scala 2.13 only
    "-Ymacro-annotations",
    // ---
    "-encoding",
    "utf8"
  )

  object libs {

    val `akka-http`: Def.Initialize[Seq[ModuleID]] = Def.setting {
      Seq(
        "com.typesafe.akka" %% "akka-http" % versions.`akka-http`
      )
    }

    val `akka-http-json`: Def.Initialize[Seq[ModuleID]] = Def.setting {
      Seq(
        "de.heikoseeberger" %% "akka-http-circe" % versions.`akka-http-json`
      )
    }

    val akka: Def.Initialize[Seq[ModuleID]] = Def.setting {
      Seq(
        "com.typesafe.akka" %% "akka-actor" % versions.akka,
        "com.typesafe.akka" %% "akka-actor-typed" % versions.akka,
        "com.typesafe.akka" %% "akka-stream" % versions.akka,
        "com.typesafe.akka" %% "akka-testkit" % versions.akka % Test
      )
    }

    val `typesafe-config`: Def.Initialize[Seq[ModuleID]] = Def.setting {
      Seq(
        "com.typesafe" % "config" % versions.`typesafe-config`
      )
    }

  }

  val backendDependencies: Def.Initialize[Seq[ModuleID]] = Def.setting {
    Seq.concat(
      libs.`akka-http`.value,
      libs.`akka-http-json`.value,
      libs.akka.value,
      libs.`typesafe-config`.value,
    )
  }
}
