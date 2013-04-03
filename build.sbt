name := "jerkson"

version := "0.7-SNAPSHOT"

scalaVersion := "2.10.0"

scalacOptions := Seq("-unchecked", "-deprecation")

libraryDependencies ++= Seq(
	"com.fasterxml.jackson.core" % "jackson-core" % "2.1.3",
	"com.fasterxml.jackson.core" % "jackson-databind" % "2.1.3",
	"org.mockito" % "mockito-all" % "1.9.0",
	"junit" % "junit" % "4.10",
	"org.hamcrest" % "hamcrest-core" % "1.3",
	"org.scalatest" %% "scalatest" % "1.9.1" % "test",
	"org.scala-lang" % "scala-reflect" % "2.10.0"
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
