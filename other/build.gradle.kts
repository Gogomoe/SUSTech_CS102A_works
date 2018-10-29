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

typealias Program = String

fun evaluateAll(vararg mains: Program) {
    mains.forEach {
        val program = it
        try {
            program.eval()
            handleAccept(program)
        } catch (e: Exception) {
            handleError(e, program)
        }
    }
}

fun findAllProgram(): List<Program> {
    return fileTree("src") { include("**/*.java") }
            .filter { it.readText().contains("public static void main(String[] args)") }
            .toList()
            .map { it.name.replace(".java", "") }
            .filter { it.inputFile().exists() }

}

fun Program.eval() {
    javaexec {
        classpath = java.sourceSets["main"].runtimeClasspath
        main = this@eval
        standardInput = FileInputStream("src/main/resources/$main.in")
        standardOutput = FileOutputStream("$buildDir/$main.res")
    }

    this.compareResult()
}

fun Program.compareResult() {
    val res = resultFile().readLines()
    val out = answerFile().readLines()

    if (res.size != out.size) {
        throw RuntimeException("$this Wrong Answer")
    }
    for (i in 0 until res.size) {
        if (res[i].trim() != out[i].trim()) {
            throw  RuntimeException("$this Wrong Answer")
        }
    }
}

fun handleAccept(program: Program) {
    println("\u001B[32m$program Accept\u001B[0m")
}

fun handleError(e: Exception, program: Program) {
    print("\u001B[31m")
    if (e.message!!.endsWith("Wrong Answer")) {
        println(e.message)
        println("your result is")
        println(program.resultFile().readText())
        println("correct answer is")
        println(program.answerFile().readText())
    } else {
        println("$program Error")
    }
    print("\u001B[0m")
}

fun Program.inputFile(): File = File("src/main/resources/$this.in")

fun Program.resultFile(): File = File("$buildDir/$this.res")

fun Program.answerFile(): File = File("src/main/resources/$this.out")
