package net.voidgroup.celestia.glfw.error;

import net.voidgroup.celestia.glfw.GLFW;
import org.jetbrains.annotations.NotNull;

@NotNull
public class GLFWException extends RuntimeException {
    private final @NotNull ErrorCode code;
    public GLFWException(@NotNull String message) {
        super(STR."\{message} (\{GLFW.getError() != null ? GLFW.getError().message() : "No error message"})");
        if(GLFW.getError() != null) code = GLFW.getError().code();
        else code = ErrorCode.NoError;
    }
    @NotNull
    public ErrorCode getCode() {
        return code;
    }
}
