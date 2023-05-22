import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.api.tasks.wrapper.Wrapper.DistributionType.ALL
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val gradleWrapperVersion: String by project
val jacocoVersion: String by project
val javaVersion: String by project
val junitVersion: String by project
val kotestVersion: String by project
val kotlinVersion: String by project
val ktlintVersion: String by project
val pitestJUnit5PluginVersion: String by project

plugins {
    kotlin("jvm")
    id("com.github.ben-manes.versions")
    id("info.solidsoft.pitest")
    // TODO: Re-enable detekt after CVE-2021-4277 and others resolved
    // id("io.gitlab.arturbosch.detekt")
    // TODO: Re-enable ktlint after CVE-2021-42550 resolved
    // id("org.jlleitschuh.gradle.ktlint")
    id("org.owasp.dependencycheck")
    jacoco
}

version = "0"
group = "hm.binkley"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
}

configurations.all {
    resolutionStrategy.eachDependency {
        if ("org.jetbrains.kotlin" == requested.group) {
            useVersion(kotlinVersion)
        }
    }
}

/*
detekt {
    // No support yet for configuring directly in Gradle
    config = files("config/detekt.yml")
}
*/

jacoco {
    toolVersion = jacocoVersion
}

/*
ktlint {
    outputColorName.set("RED")
    version.set(ktlintVersion)
}
*/

pitest {
    junit5PluginVersion.set(pitestJUnit5PluginVersion)
    // TODO: Pitest not recognizing Kotlin non-nullability
    mutationThreshold.set(86)
    timestampedReports.set(false)
}

dependencyCheck {
    failBuildOnCVSS = 0f
}

tasks {
    withType<DependencyUpdatesTask> {
        rejectVersionIf {
            !isStable(candidate.version) && isStable(currentVersion)
        }
    }

    withType<KotlinCompile> {
        kotlinOptions {
            allWarningsAsErrors = true
            jvmTarget = javaVersion
            javaParameters = true
        }
    }

    test {
        useJUnitPlatform()

        testLogging {
            showStandardStreams = true
        }

        finalizedBy(jacocoTestReport)
    }

    jacocoTestCoverageVerification {
        violationRules {
            rule {
                limit {
                    // TODO: JaCoCo does not work nicely with data classes
                    minimum = "0.97".toBigDecimal()
                }
            }
        }
    }

    /*
    ktlintCheck {
        dependsOn += ktlintFormat
    }
    */

    check {
        // dependsOn += ktlintCheck
        dependsOn += jacocoTestCoverageVerification
        dependsOn += dependencyCheckAnalyze
        dependsOn += pitest
    }

    withType<Wrapper> {
        gradleVersion = gradleWrapperVersion
        distributionType = ALL
    }
}

val otherReleasePatterns = "^[0-9,.v-]+(-r)?$".toRegex()

fun isStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any {
        version.uppercase().contains(it)
    }
    val otherReleasePattern = otherReleasePatterns.matches(version)

    return stableKeyword || otherReleasePattern
}
