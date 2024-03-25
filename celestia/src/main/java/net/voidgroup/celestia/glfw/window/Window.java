package net.voidgroup.celestia.glfw.window;

import net.voidgroup.celestia.glfw.GLFW;
import net.voidgroup.celestia.glfw.InternalClosable;
import net.voidgroup.celestia.glfw.data.Point2D;
import net.voidgroup.celestia.glfw.error.GLFWException;
import net.voidgroup.celestia.unsafe.GLFWLibrary;

import java.lang.foreign.Arena;

public class Window implements InternalClosable, AutoCloseable {
    private final long handle;
    private boolean closed;
    protected Window(String title, Point2D size, Monitor monitor, Window shared, boolean visible, boolean resizable, boolean decorated, boolean floating, boolean maximized, boolean centerCursor, boolean transparentFramebuffer, boolean focusOnShow) {
        GLFWLibrary.glfwDefaultWindowHints.execute();
        if(monitor != null) {
            var videoMode = VideoMode.fromMonitor(monitor);
            size = videoMode.size();
            WindowHint.REFRESH_RATE.setHint(videoMode.refreshRate());
            WindowHint.RED_BITS.setHint(videoMode.redBits());
            WindowHint.GREEN_BITS.setHint(videoMode.greenBits());
            WindowHint.BLUE_BITS.setHint(videoMode.blueBits());
        }
        WindowHint.VISIBLE.setHint(visible);
        WindowHint.RESIZABLE.setHint(resizable);
        WindowHint.DECORATED.setHint(decorated);
        WindowHint.FLOATING.setHint(floating);
        WindowHint.MAXIMIZED.setHint(maximized);
        WindowHint.CENTER_CURSOR.setHint(centerCursor);
        WindowHint.TRANSPARENT_FRAMEBUFFER.setHint(transparentFramebuffer);
        WindowHint.FOCUS_ON_SHOW.setHint(focusOnShow);
        var arena = Arena.ofConfined();
        var titleMemory = arena.allocateUtf8String(title);
        this.handle = GLFWLibrary.glfwCreateWindow.execute(size != null ? size.x() : 0, size != null ? size.y() : 0, titleMemory, monitor != null ? monitor.getHandle() : 0, shared != null ? shared.handle : 0);
        arena.close();
        if(this.handle == 0) throw GLFWException.fromError("Failed to create window");
        GLFW.register(this);
    }
    public boolean shouldClose() {
        if(closed) throw new IllegalStateException("Window is destroyed");
        return GLFWLibrary.glfwWindowShouldClose.execute(handle);
    }
    public void setResizable(boolean resizable) {
        WindowHint.RESIZABLE.setAttribute(handle, resizable);
    }
    @Override
    public void close(boolean nativeClose) {
        if(nativeClose && !closed && handle != 0) GLFWLibrary.glfwDestroyWindow.execute(handle);
        closed = true;
        GLFW.unregister(this);
    }

    @Override
    public void close() {
        close(true);
    }
}
