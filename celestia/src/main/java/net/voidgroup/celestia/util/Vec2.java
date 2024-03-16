package net.voidgroup.celestia.util;

import org.jetbrains.annotations.NotNull;
@NotNull
public record Vec2(int x, int y){
    @NotNull
    public Vec2 multiply(Vec2 other) {
        return new Vec2(x * other.x, y * other.y);
    }
}
