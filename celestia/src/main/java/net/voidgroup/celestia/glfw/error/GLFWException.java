package net.voidgroup.celestia.glfw.error;

import net.voidgroup.celestia.glfw.GLFW;
import org.jetbrains.annotations.NotNull;

@NotNull
public class GLFWException extends RuntimeException {
    private final @NotNull ErrorCode code;
    private GLFWException(@NotNull String message, Error error) {
        super(STR."\{message} (\{error.message()})");
        code = error.code();
    }
    public static GLFWException fromError(String message) {
        return new GLFWException(message, GLFW.getError());
    }
    @NotNull
    public ErrorCode getCode() {
        return code;
    }
}
