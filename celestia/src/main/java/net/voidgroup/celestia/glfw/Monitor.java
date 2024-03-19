package net.voidgroup.celestia.glfw;

import net.voidgroup.celestia.glfw.error.GLFWException;
import net.voidgroup.celestia.unsafe.GLFWLibrary;

public class Monitor implements NativeClosable {
    private final long handle;
    private boolean closed;
    private Monitor(long handle) {
        if(!GLFW.isInitialised()) throw new IllegalStateException("GLFW not initialised");
        GLFW.register(this);
        this.handle = handle;
    }

    public static Monitor getPrimary() {
        var handle = GLFWLibrary.glfwGetPrimaryMonitor.execute();
        if(handle == 0) throw new GLFWException("Failed to get primary monitor");
        return new Monitor(handle);
    }


    @Override
    public void close(boolean nativeClose) {
        closed = true;
        GLFW.unregister(this);
    }

    public long getHandle() {
        if(closed) throw new IllegalStateException("Monitor is destroyed");
        return handle;
    }
}
