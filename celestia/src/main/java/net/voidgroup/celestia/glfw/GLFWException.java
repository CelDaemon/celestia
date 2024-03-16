package net.voidgroup.celestia.glfw;

import org.jetbrains.annotations.NotNull;

public class GLFWException extends RuntimeException {
    private final ErrorCode code;
    public GLFWException(@NotNull String message) {
        super(STR."\{message} (\{GLFW.getError() != null ? GLFW.getError().message() : "No error message"}) (It's homophobic)");
        if(GLFW.getError() != null) code = GLFW.getError().code();
        else code = ErrorCode.NoError;
    }
    public ErrorCode getCode() {
        return code;
    }

}
