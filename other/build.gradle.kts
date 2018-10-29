import java.io.FileInputStream
import java.io.FileOutputStream

plugins {
    java
}

group = "moe.gogo"
version = "0"

repositories {
    mavenCentral()
}

dependencies {
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

task("runEvaluations") {
    dependsOn("classes")
    val programs = findAllProgram()
    doLast {
        evaluateAll(*programs.toTypedArray())
    }
}

fun evaluateAll(vararg mains: String) {
    mains.forEach {
        val program = it
        try {
            evaluateProgram(program)
            handleAccept(program)
        } catch (e: Exception) {
            handleError(e, program)
        }
    }
}

fun findAllProgram(): List<String> {
    return fileTree("src") { include("**/*.java") }
            .filter { it.readText().contains("public static void main(String[] args)") }
            .toList()
            .map { it.name.replace(".java", "") }

}

fun evaluateProgram(program: String) {
    javaexec {
        classpath = java.sourceSets["main"].runtimeClasspath
        main = program
        standardInput = FileInputStream("src/main/resources/$program.in")
        standardOutput = FileOutputStream("$buildDir/$program.res")
    }

    compareResult(program)
}

fun compareResult(program: String) {
    val res = resultFile(program).readLines()
    val out = answerFile(program).readLines()

    if (res.size != out.size) {
        throw RuntimeException("$program Wrong Answer")
    }
    for (i in 0 until res.size) {
        if (res[i].trim() != out[i].trim()) {
            throw  RuntimeException("$program Wrong Answer")
        }
    }
}

fun handleAccept(program: String) {
    println("\u001B[32m$program Accept\u001B[0m")
}

fun handleError(e: Exception, program: String) {
    print("\u001B[31m")
    if (e.message!!.endsWith("Wrong Answer")) {
        println(e.message)
        println("your result is")
        println(resultFile(program).readText())
        println("correct answer is")
        println(answerFile(program).readText())
    } else {
        println("$program Error")
    }
    print("\u001B[0m")
}

fun inputFile(program: String): File = File("src/main/resources/$program.in")

fun resultFile(program: String): File = File("$buildDir/$program.res")

fun answerFile(program: String): File = File("src/main/resources/$program.out")
