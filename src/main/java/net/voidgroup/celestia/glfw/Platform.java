package net.voidgroup.celestia.glfw;

import net.voidgroup.celestia.glfw.internal.GLFWConstants;

public enum Platform {
    Null(GLFWConstants.GLFW_PLATFORM_NULL),
    X11(GLFWConstants.GLFW_PLATFORM_X11),
    Wayland(GLFWConstants.GLFW_PLATFORM_WAYLAND),
    Cocoa(GLFWConstants.GLFW_PLATFORM_CACOA),
    Win32(GLFWConstants.GLFW_PLATFORM_WIN32),
    Any(GLFWConstants.GLFW_ANY_PLATFORM);
    public final int id;
    Platform(int id) {
        this.id = id;
    }
}
