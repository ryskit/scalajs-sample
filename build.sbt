ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.1.2"
ThisBuild / scalacOptions ++= Seq(
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-explaintypes",
  "-feature",
  "-unchecked",
  "-Ywarn-unused",
  "-Xlint"
)

lazy val root = (project in file("."))
  .settings(
    name := "scalajs-sample",
    // scalaJSUseMainModuleInitializer := true,
    libraryDependencies ++= Seq(
      // "net.exoego" %%% "aws-sdk-scalajs-facade" % "0.33.0-v2.892.0",
      "net.exoego" %%% "aws-lambda-scalajs-facade" % "0.12.1"
    ),
    scalaJSLinkerConfig ~= (_.withModuleKind(ModuleKind.CommonJSModule)),
    webpack / version := "5.72.0",
    topLevelDirectory := None,
    webpackConfigFile := Some(baseDirectory.value / "webpack.config.js"),
    Universal / mappings ++= (Compile / fullOptJS / webpack).value.map { f =>
      // remove the bundler suffix from the file names
      f.data -> f.data.getName.replace("-opt-bundle", "")
    }
//    npmDependencies += "aws-sdk" -> "2.892.0"
  )
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin, UniversalPlugin)
