package net.voidgroup.celestia.glfw;

import net.voidgroup.celestia.unsafe.GLFWLibrary;
import net.voidgroup.celestia.util.Point;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Window implements AutoCloseable {
    private final long handle;
    private final WindowContext context;
    protected Window(Point size, String title) {
        try(var arena = Arena.ofConfined()) {
            var titleBytes = Objects.requireNonNull(title).getBytes(StandardCharsets.UTF_8);
            var titleMemory = arena.allocate(titleBytes.length + 1);
            titleMemory.copyFrom(MemorySegment.ofArray(titleBytes));
            if((handle = GLFWLibrary.glfwCreateWindow.execute(size.x(), size.y(), titleMemory, MemorySegment.NULL, MemorySegment.NULL)) == 0) {
                throw new GLFWException("Failed to create window");
            }
        }
        context = new WindowContext(handle);
    }
    public void swap() {
        GLFWLibrary.glfwSwapBuffers.execute(handle);
    }
    public boolean shouldClose() {
        return GLFWLibrary.glfwWindowShouldClose.execute(handle);
    }

    public WindowContext getContext() {
        return context;
    }

    @Override
    public void close() {
        GLFWLibrary.glfwDestroyWindow.execute(handle);
        context.close();
    }
}
