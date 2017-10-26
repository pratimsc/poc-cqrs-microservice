organization := "cloud.yantra"
name := "Demo CQRS Microservice"
version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.4"
scalacOptions ++= Seq("-target:jvm-1.8",
  "-unchecked",
  "-deprecation",
  "-encoding", "utf8",
  "-feature",
  "-Ywarn-adapted-args",
  "-Ywarn-dead-code")

logLevel := sbt.Level.Warn
exportJars := false
//libraryDependencies ++= Dependencies.groupBackendDependencies

/** ****************************************************************************
  * Runtime related
  * ****************************************************************************
  */
externalDependencyClasspath in Runtime ++= sys.props.get("config.dir")
  .map(f => Seq(file(f)))
  .getOrElse(Nil)
javaOptions in Runtime +="-Dconfig.resource=conf/consumer.conf"



        