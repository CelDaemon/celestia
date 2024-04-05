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
    applicationDefaultJvmArgs = listOf("--enable-preview")
}