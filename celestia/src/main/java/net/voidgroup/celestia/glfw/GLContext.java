package net.voidgroup.celestia.glfw;

public class GLContext implements AutoCloseable {
    private boolean closed;
    @Override
    public void close() {
        closed = true;
    }
}
