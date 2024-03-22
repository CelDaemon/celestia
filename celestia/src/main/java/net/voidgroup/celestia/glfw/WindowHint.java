package net.voidgroup.celestia.glfw;

import net.voidgroup.celestia.unsafe.GLFWLibrary;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.foreign.Arena;

public record WindowHint<T>(WindowHintKey<T> key, T value) {
    public static final WindowHintKey<Boolean> VISIBLE = new WindowHintKey<>(GLFWLibrary.GLFW_VISIBLE);
    public static final WindowHintKey<Boolean> RESIZABLE = new WindowHintKey<>(GLFWLibrary.GLFW_RESIZABLE);
    public static final WindowHintKey<Boolean> DECORATED = new WindowHintKey<>(GLFWLibrary.GLFW_DECORATED);
    public static final WindowHintKey<Boolean> FLOATING = new WindowHintKey<>(GLFWLibrary.GLFW_FLOATING);
    public static final WindowHintKey<Boolean> MAXIMIZED = new WindowHintKey<>(GLFWLibrary.GLFW_MAXIMIZED);
    public static final WindowHintKey<Boolean> CENTER_CURSOR = new WindowHintKey<>(GLFWLibrary.GLFW_CENTER_CURSOR);
    public static final WindowHintKey<Boolean> TRANSPARENT_FRAMEBUFFER = new WindowHintKey<>(GLFWLibrary.GLFW_TRANSPARENT_FRAMEBUFFER);
    public static final WindowHintKey<Boolean> FOCUS_ON_SHOW = new WindowHintKey<>(GLFWLibrary.GLFW_FOCUS_ON_SHOW);
    void apply() {
        switch (value) {
            case Boolean booleanValue:
                GLFWLibrary.glfwWindowHint.execute(key.id, booleanValue ? 1 : 0);
                break;
            case Integer integerValue:
                GLFWLibrary.glfwWindowHint.execute(key.id, integerValue);
                break;
            case String stringValue:
                try(var arena = Arena.ofConfined()) {
                    var stringValueMemory = arena.allocateUtf8String(stringValue);
                    GLFWLibrary.glfwWindowHintString.execute(key.id, stringValueMemory);
                }
            default:
                throw new UnsupportedOperationException();
        }
    }
    public record WindowHintKey<T>(int id) {
        @Contract("_ -> new")
        public @NotNull WindowHint<T> of(T value) {
            return new WindowHint<>(this, value);
        }
    }
}