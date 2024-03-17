package net.voidgroup.celestia.glfw;

import net.voidgroup.celestia.unsafe.GLFWLibrary;
import net.voidgroup.celestia.unsafe.UnsafeUtil;
import net.voidgroup.celestia.util.Point;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Map;

@NotNull
public class GLFW implements AutoCloseable {
    @Nullable
    private static Error error;
    public static @Nullable Error getError() {
        return error;
    }
    private boolean closed;
    public GLFW() {
        GLFWLibrary.setErrorHandler((integer, s) -> error = new Error(ErrorCode.valueOf(integer), s));
        if(!GLFWLibrary.glfwInit.execute()) throw new RuntimeException();
    }
    @NotNull
    public String getVersionString() {
        if(closed) throw new IllegalStateException();
        return UnsafeUtil.readString(GLFWLibrary.glfwGetVersionString.execute());
    }
    @NotNull
    public Window createWindow(@NotNull Point size, @NotNull String title) {
        return createWindow(size, title, null);
    }
    @NotNull
    public Window createWindow(@NotNull Point size, @NotNull String title, Map<Integer, Integer> hints) {
        if(closed) throw new IllegalStateException();
        return new Window(size, title, hints != null ? hints : Collections.emptyMap());
    }
    public void pollEvents() {
        GLFWLibrary.glfwPollEvents.execute();
    }
    @Override
    public void close() {
        closed = true;
        GLFWLibrary.glfwTerminate.execute();
    }
}