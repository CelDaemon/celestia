package net.voidgroup.celestia.glfw.data;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
@NotNull
public record Vector2D(float x, float y) {
    public double length() {
        return Math.sqrt(x * x + y * y);
    }
    @Contract(" -> new")
    public @NotNull Vector2D normalize() {
        var len = length();
        return new Vector2D((float) (x / len),  (float) (y / len));
    }
    @Contract("_ -> new")
    public @NotNull Vector2D relativeTo(@NotNull Vector2D origin) {
        return new Vector2D(x - origin.x, y - origin.y);
    }

    @Contract("_ -> new")
    public @NotNull Vector2D multiply(@NotNull Vector2D other) {
        return new Vector2D(x * other.x, y * other.y);
    }
}
