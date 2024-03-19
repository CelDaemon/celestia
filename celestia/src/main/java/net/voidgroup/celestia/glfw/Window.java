package net.voidgroup.celestia.glfw;

import net.voidgroup.celestia.glfw.error.GLFWException;
import net.voidgroup.celestia.unsafe.GLFWLibrary;
import net.voidgroup.celestia.util.Point;
import org.jetbrains.annotations.NotNull;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
@NotNull
public class Window implements NativeClosable {
    private final GLContext context;
    private final long handle;
    private boolean closed;

    public Window(@NotNull Point size, @NotNull String title) {
        if(size.x() < 1 || size.y() < 1) throw new IllegalArgumentException("Point is less than 1");
        GLFW.getInstance().register(this);
        try(var arena = Arena.ofConfined()) {
            var titleSegment = arena.allocateUtf8String(title);
            handle = GLFWLibrary.glfwCreateWindow.execute(size.x(), size.y(), titleSegment, 0L, MemorySegment.NULL);
            if(handle == 0) throw new GLFWException("Failed to create window");
        }
        context = new GLContext();
    }
    @Override
    public void close(boolean nativeClose) {
        if(closed) return;
        if(nativeClose) GLFWLibrary.glfwDestroyWindow.execute(handle);
        context.close();
        closed = true;
    }
}
