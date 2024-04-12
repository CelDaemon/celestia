package net.voidgroup.celestia.glfw;

import net.voidgroup.celestia.glfw.internal.GLFWConstants;

public enum AnglePlatform {
    None(GLFWConstants.GLFW_ANGLE_PLATFORM_TYPE_NONE),
    OpenGL(GLFWConstants.GLFW_ANGLE_PLATFORM_TYPE_OPENGL),
    OpenGLES(GLFWConstants.GLFW_ANGLE_PLATFORM_TYPE_OPENGLES),
    D3D9(GLFWConstants.GLFW_ANGLE_PLATFORM_TYPE_D3D9),
    D3D11(GLFWConstants.GLFW_ANGLE_PLATFORM_TYPE_D3D11),
    Vulkan(GLFWConstants.GLFW_ANGLE_PLATFORM_TYPE_VULKAN),
    Metal(GLFWConstants.GLFW_ANGLE_PLATFORM_TYPE_METAL);
    private final int id;
    AnglePlatform(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
}
