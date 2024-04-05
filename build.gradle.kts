plugins {
    id("java-library")
    id("maven-publish")
}
allprojects {
    tasks.withType<JavaCompile> {
        options.compilerArgs.add("--enable-preview")
    }
}
dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    compileOnly("org.jetbrains:annotations:+")
}
tasks.test {
    useJUnitPlatform()
}
java {
    withSourcesJar()
}
publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group as String
            artifactId = project.name
            version = project.version as String
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "GithubPackages"
            url = uri("https://maven.pkg.github.com/CelDaemon/celestia")
            credentials(PasswordCredentials::class)
        }
    }
}