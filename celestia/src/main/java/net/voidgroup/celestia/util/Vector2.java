package net.voidgroup.celestia.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
@NotNull
public record Vector2(float x, float y) {
    public double length() {
        return Math.sqrt(x * x + y * y);
    }
    @Contract(" -> new")
    public @NotNull Vector2 normalize() {
        var len = length();
        return new Vector2((float) (x / len),  (float) (y / len));
    }
    @Contract("_ -> new")
    public @NotNull Vector2 relativeTo(@NotNull Vector2 origin) {
        return new Vector2(x - origin.x, y - origin.y);
    }

    @Contract("_ -> new")
    public @NotNull Vector2 multiply(@NotNull Vector2 other) {
        return new Vector2(x * other.x, y * other.y);
    }
}
