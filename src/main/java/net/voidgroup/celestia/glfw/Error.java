package net.voidgroup.celestia.glfw;

import net.voidgroup.celestia.glfw.internal.GLFWConstants;

import java.lang.foreign.MemorySegment;
import java.util.Arrays;

public record Error(ErrorCode code, String message) {
    @Override
    public String toString() {
        return STR."\{message} (\{code})";
    }
    public static Error fromNative(int code, MemorySegment messageSegment) {
        return new Error(ErrorCode.fromId(code), messageSegment.reinterpret(512).getUtf8String(0));
    }

    public enum ErrorCode {
        NoError(GLFWConstants.GLFW_NO_ERROR),
        NotInitialised(GLFWConstants.GLFW_NOT_INITIALIZED),
        NoCurrentContext(GLFWConstants.GLFW_NO_CURRENT_CONTEXT),
        InvalidEnum(GLFWConstants.GLFW_INVALID_ENUM),
        InvalidValue(GLFWConstants.GLFW_INVALID_VALUE),
        OutOfMemory(GLFWConstants.GLFW_OUT_OF_MEMORY),
        ApiUnavailable(GLFWConstants.GLFW_API_UNAVAILABLE),
        VersionUnavailable(GLFWConstants.GLFW_VERSION_UNAVAILABLE),
        PlatformError(GLFWConstants.GLFW_PLATFORM_ERROR),
        FormatUnavailable(GLFWConstants.GLFW_FORMAT_UNAVAILABLE),
        NoWindowContext(GLFWConstants.GLFW_NO_WINDOW_CONTEXT);
        private final int id;
        public static ErrorCode fromId(int id) {
            return Arrays.stream(ErrorCode.values()).filter(code -> code.id == id).findFirst().orElse(null);
        }
        ErrorCode(int id) {
            this.id = id;
        }
    }
}
