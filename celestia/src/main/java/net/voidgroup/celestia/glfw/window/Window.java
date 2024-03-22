package net.voidgroup.celestia.glfw.window;

import net.voidgroup.celestia.glfw.GLFW;
import net.voidgroup.celestia.glfw.InternalClosable;
import net.voidgroup.celestia.glfw.data.Point2D;
import net.voidgroup.celestia.glfw.error.GLFWException;
import net.voidgroup.celestia.unsafe.GLFWLibrary;

import java.lang.foreign.Arena;
import java.util.Collection;

public class Window implements InternalClosable, AutoCloseable {
    private final long handle;
    private boolean closed;
    protected Window(String title, Point2D size, Monitor monitor, Window shared, Collection<WindowHint<?>> hints) {
        if(monitor != null) {
            var videoMode = VideoMode.fromMonitor(monitor);
            size = videoMode.size();
            hints.add(WindowHint.REFRESH_RATE.of(videoMode.refreshRate()));
            hints.add(WindowHint.RED_BITS.of(videoMode.redBits()));
            hints.add(WindowHint.GREEN_BITS.of(videoMode.greenBits()));
            hints.add(WindowHint.BLUE_BITS.of(videoMode.blueBits()));
        }
        GLFWLibrary.glfwDefaultWindowHints.execute();
        hints.forEach(WindowHint::apply);
        if(size == null) size = new Point2D(0, 0);
        var arena = Arena.ofConfined();
        var titleMemory = arena.allocateUtf8String(title);
        this.handle = GLFWLibrary.glfwCreateWindow.execute(size.x(), size.y(), titleMemory, monitor != null ? monitor.getHandle() : 0, shared != null ? shared.handle : 0);
        arena.close();
        if(this.handle == 0) throw new GLFWException("Failed to create window");
        GLFW.register(this);
    }

    @Override
    public void close(boolean nativeClose) {
        if(nativeClose && !closed && handle != 0) GLFWLibrary.glfwDestroyWindow.execute(handle);
        GLFW.unregister(this);
        closed = true;
    }

    @Override
    public void close() {
        close(true);
    }
}
