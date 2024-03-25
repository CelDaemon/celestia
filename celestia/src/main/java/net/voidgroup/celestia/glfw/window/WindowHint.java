package net.voidgroup.celestia.glfw.window;

import net.voidgroup.celestia.unsafe.GLFWLibrary;

import java.lang.foreign.Arena;

public record WindowHint<T>(int id) {
    public static final WindowHint<Integer> REFRESH_RATE = new WindowHint<>(GLFWLibrary.GLFW_REFRESH_RATE);
    public static final WindowHint<Integer> RED_BITS = new WindowHint<>(GLFWLibrary.GLFW_RED_BITS);
    public static final WindowHint<Integer> GREEN_BITS = new WindowHint<>(GLFWLibrary.GLFW_GREEN_BITS);
    public static final WindowHint<Integer> BLUE_BITS = new WindowHint<>(GLFWLibrary.GLFW_BLUE_BITS);
    public static final WindowHint<Boolean> VISIBLE = new WindowHint<>(GLFWLibrary.GLFW_VISIBLE);
    public static final WindowHint<Boolean> RESIZABLE = new WindowHint<>(GLFWLibrary.GLFW_RESIZABLE);
    public static final WindowHint<Boolean> DECORATED = new WindowHint<>(GLFWLibrary.GLFW_DECORATED);
    public static final WindowHint<Boolean> FLOATING = new WindowHint<>(GLFWLibrary.GLFW_FLOATING);
    public static final WindowHint<Boolean> MAXIMIZED = new WindowHint<>(GLFWLibrary.GLFW_MAXIMIZED);
    public static final WindowHint<Boolean> CENTER_CURSOR = new WindowHint<>(GLFWLibrary.GLFW_CENTER_CURSOR);
    public static final WindowHint<Boolean> TRANSPARENT_FRAMEBUFFER = new WindowHint<>(GLFWLibrary.GLFW_TRANSPARENT_FRAMEBUFFER);
    public static final WindowHint<Boolean> FOCUS_ON_SHOW = new WindowHint<>(GLFWLibrary.GLFW_FOCUS_ON_SHOW);
    public void setHint(T value) {
        switch (value) {
            case Integer intValue:
                GLFWLibrary.glfwWindowHint.execute(id, intValue);
                break;
            case Boolean boolValue:
                GLFWLibrary.glfwWindowHint.execute(id, boolValue ? 1 : 0);
                break;
            case String stringValue:
                try(var arena = Arena.ofConfined()) {
                    var stringValueMemory = arena.allocateUtf8String(stringValue);
                    GLFWLibrary.glfwWindowHintString.execute(id, stringValueMemory);
                }
                break;
            default:
                throw new IllegalStateException();
        }
    }
    public void setAttribute(long window, T value) {
        switch (value) {
            case Integer intValue:
                GLFWLibrary.glfwSetWindowAttrib.execute(window, id, intValue);
                break;
            case Boolean boolValue:
                GLFWLibrary.glfwSetWindowAttrib.execute(window, id, boolValue ? 1 : 0);
                break;
            default:
                throw new IllegalStateException();
        }
    }
}
