package net.voidgroup.celestia.glfw;

import net.voidgroup.celestia.glfw.internal.GLFWConstants;

public enum GLFWHint {
    Platform(GLFWConstants.GLFW_PLATFORM);
    public final int id;
    GLFWHint(int id) {
        this.id = id;
    }
}
