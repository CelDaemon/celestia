package net.voidgroup.celestia.glfw.error;

import org.jetbrains.annotations.NotNull;

@NotNull
public record Error(@NotNull ErrorCode code, @NotNull String message) {
}
