package net.voidgroup.celestia.glfw;

import net.voidgroup.celestia.unsafe.UnsafeUtil;
import net.voidgroup.celestia.unsafe.glfw.GLFWLibrary;
import net.voidgroup.celestia.util.Vec2;

public class GLFW implements AutoCloseable {
    private boolean closed;
    private Error error;
    public GLFW() {
        GLFWLibrary.setErrorHandler((integer, s) -> error = new Error(integer, s));
        if(!GLFWLibrary.glfwInit.execute()) throw new RuntimeException();
    }
    public Error getError() {
        return error;
    }
    public String getVersionString() {
        return UnsafeUtil.readString(GLFWLibrary.glfwGetVersionString.execute());
    }
    public Window createWindow(Vec2<Integer> size, String title) {
        if(closed) throw new IllegalStateException();
        return new Window(this, size, title);
    }
    @Override
    public void close() {
        closed = true;
        GLFWLibrary.glfwTerminate.execute();
    }
}