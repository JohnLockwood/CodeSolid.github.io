import sbt._
import Keys._

object HelloBuild extends Build {

/*
	val sampleKeyA = SettingKey[String]("sample-a", "demo key A")
	val sampleKeyB = SettingKey[String]("sample-b", "demo key B")
	val sampleKeyC = SettingKey[String]("sample-c", "demo key C")
	val sampleKeyD = SettingKey[String]("sample-d", "demo key D")

	override lazy val settings = super.settings ++
	    Seq(sampleKeyA := "A: in Build.settings in Build.scala", resolvers := Seq())
*/
	lazy val root = Project(id = "helloThere",
	                        base = file("."),
	                        settings =  Seq(
	                        	/*sampleKeyB := "B: in the root project settings in Build.scala", */
	                        	helloAgainTask,
	                        	helloTask,
	                        	oneTask,
	                        	twoTask,
	                        	threeTask	                        
	                        	))

	val hello = TaskKey[Unit]("hello", "Prints 'Hello World'")

	val helloAgain = TaskKey[Unit]("helloAgain", "Prints 'Hello Again'")
	

	val helloAgainTask = helloAgain <<= (hello) map { _ => println("Hello Again")	}

	val helloTask = hello := {
		println("Hello World")
	}

	val one = TaskKey[Unit]("one", "Prints one")
	val two = TaskKey[Unit]("two", "Prints two")
	val three = TaskKey[Unit]("three", "Prints three")

	val oneTask = one := { 
		println("one") 
	}
	
	val twoTask = two := { 
		println("two") 
	}
	
	val threeTask = three := { 
		println("three") 
	}

	helloAgain <<= helloAgain.dependsOn(hello)
	
}
