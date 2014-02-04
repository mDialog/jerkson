name := "rickb777-jerkson"

version := "0.8"

scalaVersion := "2.10.1"

scalacOptions := Seq("-unchecked", "-deprecation", "-feature", "-language:postfixOps", "-language:reflectiveCalls", "-language:implicitConversions")

libraryDependencies ++= Seq(
	"com.fasterxml.jackson.core" % "jackson-core" % "2.2.0",
	"com.fasterxml.jackson.core" % "jackson-databind" % "2.2.0",
	"org.scalatest" %% "scalatest" % "1.9.1" % "test"
)

resolvers ++= Seq(
	"Sonatype OSS Snapshots Repository" at "http://oss.sonatype.org/content/groups/public/",
	"scala-tools" at "https://oss.sonatype.org/content/groups/scala-tools/",
	"sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
	"NativeLibs4Java Repository" at "http://nativelibs4java.sourceforge.net/maven/",
	"Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
	"spray repo" at "http://repo.spray.io/",
	"maven repo" at "http://repo1.maven.org/maven2/",
	"mDialog snapshots" at "http://artifactory.mdialog.com/artifactory/snapshots",
	"mDialog releases" at "http://artifactory.mdialog.com/artifactory/releases"
)

publishTo in ThisBuild <<= version { (v: String) =>
  if (v.trim.endsWith("-SNAPSHOT"))
    Some("mDialog snapshots" at "http://artifactory.mdialog.com/artifactory/snapshots")
  else
    Some("mDialog releases" at "http://artifactory.mdialog.com/artifactory/releases")
}
