val kotlinVersion = "1.2.70"
val log4jVersion = "[2.6, 2.999.999]"

plugins {
    kotlin("jvm").version("1.2.70")
    id("com.atlassian.performance.tools.gradle-release").version("0.7.1")
}

configurations.all {
    resolutionStrategy {
        activateDependencyLocking()
        failOnVersionConflict()
        eachDependency {
            when (requested.module.toString()) {
                // conflict between concurrency (this) and jvm-tasks
                // Gradle disappoints, by not picking log4j-api:2.17.0, which matches both jvm-tasks:1.2.4 and our range
                "org.apache.logging.log4j:log4j-api" -> useVersion(log4jVersion)
            }
            when (requested.group) {
                "org.jetbrains.kotlin" -> useVersion(kotlinVersion)
            }
        }
    }
}

dependencies {
    implementation("com.atlassian.performance.tools:jvm-tasks:[1.4.0, 2.0.0)")
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    api("org.apache.logging.log4j:log4j-api:$log4jVersion")
    testImplementation("org.apache.logging.log4j:log4j-core:$log4jVersion")
    testCompile("junit:junit:4.12")
    testCompile("org.hamcrest:hamcrest-library:1.3")
}

tasks.getByName("wrapper", Wrapper::class).apply {
    gradleVersion = "5.2.1"
    distributionType = Wrapper.DistributionType.ALL
}
