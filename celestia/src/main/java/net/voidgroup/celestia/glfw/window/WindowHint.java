package net.voidgroup.celestia.glfw.window;

import net.voidgroup.celestia.unsafe.GLFWLibrary;

import java.lang.foreign.Arena;

public record WindowHint<T>(WindowHintKey<T> id, T value) {
    public static final WindowHintKey<Integer> REFRESH_RATE = new WindowHintKey<>(0x0002100F);
    public static final WindowHintKey<Integer> RED_BITS = new WindowHintKey<>(0x00021001);
    public static final WindowHintKey<Integer> GREEN_BITS = new WindowHintKey<>(0x00021002);
    public static final WindowHintKey<Integer> BLUE_BITS = new WindowHintKey<>(0x00021003);
    public static final WindowHintKey<Boolean> VISIBLE = new WindowHintKey<>(GLFWLibrary.GLFW_VISIBLE);
    public static final WindowHintKey<Boolean> RESIZABLE = new WindowHintKey<>(GLFWLibrary.GLFW_RESIZABLE);
    public static final WindowHintKey<Boolean> DECORATED = new WindowHintKey<>(GLFWLibrary.GLFW_DECORATED);
    public static final WindowHintKey<Boolean> FLOATING = new WindowHintKey<>(GLFWLibrary.GLFW_FLOATING);
    public static final WindowHintKey<Boolean> MAXIMIZED = new WindowHintKey<>(GLFWLibrary.GLFW_MAXIMIZED);
    public static final WindowHintKey<Boolean> CENTER_CURSOR = new WindowHintKey<>(GLFWLibrary.GLFW_CENTER_CURSOR);
    public static final WindowHintKey<Boolean> TRANSPARENT_FRAMEBUFFER = new WindowHintKey<>(GLFWLibrary.GLFW_TRANSPARENT_FRAMEBUFFER);
    public static final WindowHintKey<Boolean> FOCUS_ON_SHOW = new WindowHintKey<>(GLFWLibrary.GLFW_FOCUS_ON_SHOW);
    public void apply() {
        switch (value) {
            case Integer intValue:
                GLFWLibrary.glfwWindowHint.execute(id.id, intValue);
                break;
            case Boolean boolValue:
                GLFWLibrary.glfwWindowHint.execute(id.id, boolValue ? 1 : 0);
                break;
            case String stringValue:
                try(var arena = Arena.ofConfined()) {
                    var stringValueMemory = arena.allocateUtf8String(stringValue);
                    GLFWLibrary.glfwWindowHintString.execute(id.id, stringValueMemory);
                }
                break;
            default:
                throw new IllegalStateException();
        }
    }
    public record WindowHintKey<T>(int id) {
        public WindowHint<T> of(T value) {
            return new WindowHint<>(this, value);
        }
    }
}
