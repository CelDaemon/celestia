package net.voidgroup.celestia.glfw.window;

import net.voidgroup.celestia.glfw.internal.GLFWConstants;

public enum WindowHint {
    Focused(GLFWConstants.GLFW_FOCUSED),
    Iconified(GLFWConstants.GLFW_ICONIFIED),
    Resizable(GLFWConstants.GLFW_RESIZABLE),
    Visible(GLFWConstants.GLFW_VISIBLE),
    Decorated(GLFWConstants.GLFW_DECORATED),
    AutoIconify(GLFWConstants.GLFW_AUTO_ICONIFY),
    Floating(GLFWConstants.GLFW_FLOATING),
    Maximized(GLFWConstants.GLFW_MAXIMIZED),
    CenterCursor(GLFWConstants.GLFW_CENTER_CURSOR),
    TransparentFramebuffer(GLFWConstants.GLFW_TRANSPARENT_FRAMEBUFFER),
    Hovered(GLFWConstants.GLFW_HOVERED),
    FocusOnShow(GLFWConstants.GLFW_FOCUS_ON_SHOW),
    RedBits(GLFWConstants.GLFW_RED_BITS),
    GreenBits(GLFWConstants.GLFW_GREEN_BITS),
    BlueBits(GLFWConstants.GLFW_BLUE_BITS),
    AlphaBits(GLFWConstants.GLFW_ALPHA_BITS),
    DepthBits(GLFWConstants.GLFW_DEPTH_BITS),
    StencilBits(GLFWConstants.GLFW_STENCIL_BITS),
    AccumRedBits(GLFWConstants.GLFW_ACCUM_RED_BITS),
    AccumGreenBits(GLFWConstants.GLFW_ACCUM_GREEN_BITS),
    AccumBlueBits(GLFWConstants.GLFW_ACCUM_BLUE_BITS),
    AccumAlphaBits(GLFWConstants.GLFW_ACCUM_ALPHA_BITS),
    AuxBuffers(GLFWConstants.GLFW_AUX_BUFFERS),
    Stereo(GLFWConstants.GLFW_STEREO),
    Samples(GLFWConstants.GLFW_SAMPLES),
    SRGBCapable(GLFWConstants.GLFW_SRGB_CAPABLE),
    RefreshRate(GLFWConstants.GLFW_REFRESH_RATE),
    DoubleBuffer(GLFWConstants.GLFW_DOUBLEBUFFER),
    ClientAPI(GLFWConstants.GLFW_CLIENT_API),
    ContextVersionMajor(GLFWConstants.GLFW_CONTEXT_VERSION_MAJOR),
    ContextVersionMinor(GLFWConstants.GLFW_CONTEXT_VERSION_MINOR),
    ContextVersionRevision(GLFWConstants.GLFW_CONTEXT_REVISION),
    ContextRobustness(GLFWConstants.GLFW_CONTEXT_ROBUSTNESS),
    OpenGLForwardCompat(GLFWConstants.GLFW_OPENGL_FORWARD_COMPAT),
    OpenGLDebugContext(GLFWConstants.GLFW_OPENGL_DEBUG_CONTEXT),
    OpenGLProfile(GLFWConstants.GLFW_OPENGL_PROFILE),
    ContextReleaseBehaviour(GLFWConstants.GLFW_CONTEXT_RELEASE_BEHAVIOR),
    ContextNoError(GLFWConstants.GLFW_CONTEXT_NO_ERROR),
    ContextCreationAPI(GLFWConstants.GLFW_CONTEXT_CREATION_API),
    ScaleToMonitor(GLFWConstants.GLFW_SCALE_TO_MONITOR),
    CocoaRetinaFramebuffer(GLFWConstants.GLFW_COCOA_RETINA_FRAMEBUFFER),
    CocoaFrameName(GLFWConstants.GLFW_COCOA_FRAME_NAME),
    CocoaGraphicsSwitching(GLFWConstants.GLFW_COCOA_GRAPHICS_SWITCHING),
    X11ClassName(GLFWConstants.GLFW_X11_CLASS_NAME),
    X11InstanceName(GLFWConstants.GLFW_X11_INSTANCE_NAME);
    public final int id;
    WindowHint(int id) {
        this.id = id;
    }
}
