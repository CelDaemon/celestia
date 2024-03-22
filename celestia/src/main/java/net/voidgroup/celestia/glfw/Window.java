package net.voidgroup.celestia.glfw;

import net.voidgroup.celestia.glfw.error.GLFWException;
import net.voidgroup.celestia.unsafe.GLFWLibrary;
import net.voidgroup.celestia.util.Point;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.util.Collection;

@NotNull
public class Window implements NativeClosable {
    private final GLContext context;
    private final long handle;
    private boolean closed;
    protected Window(@NotNull Point size,
                     @NotNull String title,
                     @Nullable Monitor monitor,
                     @NotNull Collection<WindowHint<?>> hints) {
        if(!GLFW.isInitialised()) throw new IllegalArgumentException("GLFW not initialised");
        GLFW.register(this);
        GLFWLibrary.glfwDefaultWindowHints.execute();
        hints.forEach(WindowHint::apply);
        try(var arena = Arena.ofConfined()) {
            var titleSegment = arena.allocateUtf8String(title);
            handle = GLFWLibrary.glfwCreateWindow.execute(size.x(), size.y(), titleSegment, monitor != null ? monitor.getHandle() : 0, MemorySegment.NULL);
            if(handle == 0) throw new GLFWException("Failed to create window");
        }
        context = new WindowContext();
    }
    public boolean shouldClose() {
        if(closed) throw new IllegalStateException("Window is destroyed");
        return GLFWLibrary.glfwWindowShouldClose.execute(handle);
    }
    public GLContext getContext() {
        if(closed) throw new IllegalArgumentException("Window is destroyed");
        return context;
    }
    public void swap() {
        if(closed) throw new IllegalArgumentException("Window is destroyed");
        GLFWLibrary.glfwSwapBuffers.execute(handle);
    }
    @Override
    public void close(boolean nativeClose) {
        if(closed) return;
        if(nativeClose) GLFWLibrary.glfwDestroyWindow.execute(handle);
        if(context != null) context.close();
        closed = true;
        GLFW.unregister(this);
    }
    class WindowContext extends GLContext {
        @Override
        protected void internalMakeCurrent() {
            GLFWLibrary.glfwMakeContextCurrent.execute(handle);
        }
    }
}
