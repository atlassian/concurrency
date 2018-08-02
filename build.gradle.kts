val kotlinVersion = "1.2.30"

plugins {
    kotlin("jvm").version("1.2.30")
    id("com.atlassian.performance.tools.gradle-release").version("0.0.2")
}

dependencies {
    compile("com.atlassian.performance.tools:jvm-tasks:0.0.1")
    compile("org.jetbrains.kotlin:kotlin-stdlib-jre8:$kotlinVersion")
    log4j(
        "api",
        "core",
        "slf4j-impl"
    ).forEach { compile(it) }
    testCompile("junit:junit:4.12")
    testCompile("org.hamcrest:hamcrest-library:1.3")
}

fun log4j(
    vararg modules: String
): List<String> = modules.map { module ->
    "org.apache.logging.log4j:log4j-$module:2.10.0"
}

val wrapper = tasks["wrapper"] as Wrapper
wrapper.gradleVersion = "4.9"
wrapper.distributionType = Wrapper.DistributionType.ALL
