package net.voidgroup.celestia.glfw;

import net.voidgroup.celestia.unsafe.UnsafeUtil;
import net.voidgroup.celestia.unsafe.GLFWLibrary;
import net.voidgroup.celestia.util.Point;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
        if(closed) throw new IllegalStateException();
        return new Window(size, title);
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