package net.voidgroup.celestia.glfw;

import net.voidgroup.celestia.glfw.internal.GLFWMethods;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GLFW implements AutoCloseable {
    private static final Logger LOGGER = Logger.getLogger("GLFW");
    private static final Version EXPECTED_VERSION = new Version(3, 4, 0);
    private static GLFW instance;
    public static GLFW initialise() {
        if(instance != null) return instance;
        instance = new GLFW();
        return instance;
    }
    public static void setPlatform(Platform platform) {
        GLFWMethods.glfwInitHint(GLFWHint.Platform, platform.id);
    }
    public static void setJoystickHatButtons(boolean enabled) {
        GLFWMethods.glfwInitHint(GLFWHint.JoystickHatButtons, enabled);
    }
    public static void setAnglePlatform(AnglePlatform platform) {
        GLFWMethods.glfwInitHint(GLFWHint.AnglePlatformType, platform.getId());
    }
    public static void setChdirResources(boolean enabled) {
        GLFWMethods.glfwInitHint(GLFWHint.CocoaChdirResources, enabled);
    }
    public static void setMenubar(boolean enabled) {
        GLFWMethods.glfwInitHint(GLFWHint.CocoaMenubar, enabled);
    }
    public static void setXcbVulkanSurface(boolean enabled) {
        GLFWMethods.glfwInitHint(GLFWHint.X11XCBVulkanSurface, enabled);
    }
    public static void setLibdecor(boolean enabled) {
        GLFWMethods.glfwInitHint(GLFWHint.WaylandLibdecor, enabled);
    }
    private GLFW() {
        LOGGER.log(Level.CONFIG, "Using GLFW version: {0}", getVersionString());
        var version = getVersion();
        if(!version.compareCompatible(EXPECTED_VERSION))
            LOGGER.log(Level.SEVERE, "Current GLFW version is unsupported, expected: {0}, found: {1}", new Version[]{EXPECTED_VERSION, version});
        GLFWMethods.glfwSetErrorCallback(err -> LOGGER.log(Level.SEVERE, "Native error occurred: {0}", err));
        if(!GLFWMethods.glfwInit())
            throw new RuntimeException(GLFWMethods.glfwGetError().toString());
    }
    public static @NotNull Version getVersion() {
        return GLFWMethods.glfwGetVersion();
    }
    public static String getVersionString() {
        return GLFWMethods.glfwGetVersionString();
    }

    @Override
    public void close() {
        GLFWMethods.glfwTerminate();
        instance = null;
    }
}
