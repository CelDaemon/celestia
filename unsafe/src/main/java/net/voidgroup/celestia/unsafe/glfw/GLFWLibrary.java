package net.voidgroup.celestia.unsafe.glfw;

import net.voidgroup.celestia.unsafe.SharedLibraryProvider;

import java.nio.file.Path;

public class GLFWLibrary {
    static {
        Path path = Path.of(GLFWLibrary.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        var osName = System.getProperty("os.name");
        if(osName.startsWith("Windows")) {
            path = path.resolve("glfw.dll");
        } else if (osName.startsWith("Linux") || osName.startsWith("FreeBSD")) {
            path = path.resolve("libglfw.so");
        } else {
            throw new RuntimeException();
        }
        PROVIDER = new SharedLibraryProvider(path.toAbsolutePath());
    }
    public static final SharedLibraryProvider PROVIDER;
}
