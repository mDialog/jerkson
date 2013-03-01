name := "jerkson"

version := "0.6.0-SNAPSHOT"

scalaVersion := "2.9.1"

scalacOptions ++= Seq("-unchecked", "-deprecation")

libraryDependencies ++= Seq(
	"com.fasterxml.jackson.core" % "jackson-core" % "2.1.3" ,
	"com.fasterxml.jackson.core" % "jackson-databind" % "2.1.3" ,
	"com.codahale" %% "simplespec" % "0.5.2"
)

libraryDependencies ++= Seq(
	"junit" % "junit" % "4.8.1" % "test",
	"com.typesafe.akka" % "akka-testkit" % "2.0.3" % "test",
	"org.scalatest" %% "scalatest" % "1.7.1" % "test"
)

resolvers ++= Seq(
	"mDialog snapshots" at "http://artifactory.mdialog.com/artifactory/snapshots",
	"mDialog releases" at "http://artifactory.mdialog.com/artifactory/releases",
	"Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
	"Codehaus" at "http://repository.codehaus.org",
	"Codahale" at "http://repo.codahale.com"
	)

publishTo in ThisBuild <<= version { (v: String) =>
  if (v.trim.endsWith("-SNAPSHOT"))
    Some("mDialog snapshots" at "http://artifactory.mdialog.com/artifactory/snapshots")
  else
    Some("mDialog releases" at "http://artifactory.mdialog.com/artifactory/releases")
}
