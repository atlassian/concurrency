val kotlinVersion = "1.2.70"
val log4jVersion = "2.17.1"

plugins {
    kotlin("jvm").version("1.2.70")
    id("com.atlassian.performance.tools.gradle-release").version("0.7.1")
}

configurations.all {
    resolutionStrategy {
        activateDependencyLocking()
        failOnVersionConflict()
        eachDependency {
            when (requested.group) {
                "org.jetbrains.kotlin" -> useVersion(kotlinVersion)
            }
        }
    }
}

dependencies {
    implementation("com.atlassian.performance.tools:jvm-tasks:[1.0.0,2.0.0)")
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
