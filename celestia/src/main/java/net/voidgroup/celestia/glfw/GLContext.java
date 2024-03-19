package net.voidgroup.celestia.glfw;

import net.voidgroup.celestia.unsafe.GLLibrary;

public abstract class GLContext implements AutoCloseable {
    private static GLContext current;
    private final GLLibrary library;
    private boolean closed;

    protected GLContext() {
        makeCurrent();
        library = new GLLibrary();
    }

    protected abstract void internalMakeCurrent();
    private void makeCurrent() {
        if(closed) throw new IllegalStateException("Context is destroyed");
        if(isCurrent()) return;
        internalMakeCurrent();
        current = this;
    }
    public boolean isCurrent() {
        return current == this;
    }
    public void clear(Color color) {
        if(closed) throw new IllegalStateException("Context is destroyed");
        if(!isCurrent()) makeCurrent();
        library.glClearColor.execute(color.r(), color.g(), color.b(), color.a());
        library.glClear.execute(GLLibrary.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void close() {
        closed = true;
    }
}
