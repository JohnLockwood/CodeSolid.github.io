name := "Hello"


version := "1.0"

scalaVersion := "2.11.2"

lazy val helloSBT = taskKey[Unit]("Prints 'Hello World'")

helloSBT := println("hello SBT world!")

lazy val helloAgainSBT = taskKey[Unit]("Prints 'Hello Again'")

helloAgainSBT := println("hello again (SBT)")

helloAgainSBT <<= helloAgainSBT.dependsOn(helloSBT, hello)

lazy val countTheDependencies = taskKey[Unit]("Shows dependency ordering") 

countTheDependencies := println("Dependencies counted!")

countTheDependencies <<= countTheDependencies.dependsOn(one, two, three)