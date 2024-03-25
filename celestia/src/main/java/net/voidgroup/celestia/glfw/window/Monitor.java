package net.voidgroup.celestia.glfw.window;

import net.voidgroup.celestia.glfw.InternalClosable;
import net.voidgroup.celestia.glfw.error.GLFWException;
import net.voidgroup.celestia.unsafe.GLFWLibrary;

public class Monitor implements InternalClosable {
    private final long handle;
    private static Monitor PRIMARY_MONITOR;

    public Monitor(long handle) {
        this.handle = handle;
    }
    private boolean closed;
    public static Monitor getPrimary() {
        if(PRIMARY_MONITOR != null && !PRIMARY_MONITOR.closed) return PRIMARY_MONITOR;
        var handle = GLFWLibrary.glfwGetPrimaryMonitor.execute();
        if(handle == 0) throw GLFWException.fromError("Failed to get primary monitor");
        var monitor = new Monitor(handle);
        PRIMARY_MONITOR = monitor;
        return monitor;
    }

    @Override
    public void close(boolean nativeClose) {
        closed = true;
    }

    public long getHandle() {
        return handle;
    }
}
