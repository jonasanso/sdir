name := "sdir"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies += "com.lihaoyi" %% "os-lib" % "0.6.2"

enablePlugins(GraalVMNativeImagePlugin)

assemblyJarName in assembly := "sdir.jar"
mainClass in assembly := Some("sdir.Run")

graalVMNativeImageGraalVersion := Some("20.0.0-java11")

graalVMNativeImageOptions ++= Seq(
  "--no-fallback",
  "--initialize-at-build-time",
  "--enable-http",
  "--enable-https",
  "--enable-all-security-services"
)

