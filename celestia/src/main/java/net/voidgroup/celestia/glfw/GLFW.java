package net.voidgroup.celestia.glfw;

import net.voidgroup.celestia.unsafe.glfw.GLFWLibrary;

public class GLFW implements AutoCloseable {
    private boolean closed;
    private Error error;
    public GLFW() {
        GLFWLibrary.setErrorHandler((integer, s) -> {
            error = new Error(integer, s);
        });
        if(!GLFWLibrary.glfwInit.execute()) throw new RuntimeException();
    }
    public Window createWindow(int width, int height, String title) {
        if(closed) throw new IllegalStateException();
        return new Window(width, height, title);
    }
    @Override
    public void close() {
        closed = true;
        GLFWLibrary.glfwTerminate.execute();
    }
}