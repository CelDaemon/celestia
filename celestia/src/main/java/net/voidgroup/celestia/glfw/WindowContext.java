package net.voidgroup.celestia.glfw;

import net.voidgroup.celestia.unsafe.GLFWLibrary;
import net.voidgroup.celestia.unsafe.GLLibrary;

public class WindowContext implements AutoCloseable {
    private final GLLibrary glLibrary;
    private final long handle;
    private boolean closed;
    protected WindowContext(long handle) {
        this.handle = handle;
        makeCurrent();
        glLibrary = new GLLibrary();
    }
    public void makeCurrent() {
        if(closed) throw new IllegalStateException();
        GLFWLibrary.glfwMakeContextCurrent.execute(handle);
    }

    @Override
    public void close() {
        this.closed = true;
    }

    public void clearColor(float r, float g, float b, float a) {
        glLibrary.glClearColor.execute(r, g, b, a);
        glLibrary.glClear.execute(GLLibrary.GL_COLOR_BUFFER_BIT);
    }
    public void clearColor(byte r, byte g, byte b, byte a) {
        clearColor(1f / 256 * Byte.toUnsignedInt(r), 1f / 256 * Byte.toUnsignedInt(g), 1f / 256 * Byte.toUnsignedInt(b), 1f / 256 * Byte.toUnsignedInt(a));
    }
}
