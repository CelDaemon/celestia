package net.voidgroup.celestia.glfw;

import net.voidgroup.celestia.glfw.internal.GLFWConstants;

public enum GLFWHint {
    Platform(GLFWConstants.GLFW_PLATFORM),
    JoystickHatButtons(GLFWConstants.GLFW_JOYSTICK_HAT_BUTTONS),
    AnglePlatformType(GLFWConstants.GLFW_ANGLE_PLATFORM_TYPE),
    CocoaChdirResources(GLFWConstants.GLFW_COCOA_CHDIR_RESOURCES),
    CocoaMenubar(GLFWConstants.GLFW_COCOA_MENUBAR),
    X11XCBVulkanSurface(GLFWConstants.GLFW_X11_XCB_VULKAN_SURFACE),
    WaylandLibdecor(GLFWConstants.GLFW_WAYLAND_LIBDECOR);
    public final int id;
    GLFWHint(int id) {
        this.id = id;
    }
}
