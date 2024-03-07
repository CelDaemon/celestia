package net.voidgroup.celestia.glfw;

import net.voidgroup.celestia.unsafe.glfw.GLFWLibrary;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.nio.charset.StandardCharsets;

public class Window implements AutoCloseable {
    private final long handle;
    protected Window(int width, int height, String title) {
        try(var arena = Arena.ofConfined()) {
            var titleBytes = title.getBytes(StandardCharsets.UTF_8);
            var titleMemory = arena.allocate(titleBytes.length + 1);
            titleMemory.copyFrom(MemorySegment.ofArray(titleBytes));
            if((handle = GLFWLibrary.glfwCreateWindow.execute(width, height, titleMemory, MemorySegment.NULL, MemorySegment.NULL)) == 0) {
                throw new RuntimeException(STR."Failed to create window (\{GLFWLibrary.getError().message()})");
            }
        }
    }

    @Override
    public void close() {
        GLFWLibrary.glfwDestroyWindow.execute(handle);
    }
}
