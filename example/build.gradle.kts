plugins {
    id("application")
}
dependencies {
    implementation(rootProject)
    compileOnly("org.jetbrains:annotations:+")
}
application {
    mainModule = "net.voidgroup.celestia.example"
    mainClass = "net.voidgroup.celestia.example.Main"
    applicationDefaultJvmArgs = listOf("--enable-preview", "--enable-native-access=net.voidgroup.celestia")
}
tasks.withType<JavaExec> {
    systemProperty("java.util.logging.config.file", project.file("logging.properties"))
    workingDir = project.mkdir("run")
}