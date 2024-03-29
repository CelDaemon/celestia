package net.voidgroup.celestia.test;

import net.voidgroup.celestia.glfw.data.Vector2D;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Vector2DTest {
    @ParameterizedTest
    @CsvSource(
            {
                    "20,20,10,10,2,2",
                    "15,30,5,5,3,6"
            })
    void multiply(int ox, int oy, int x1, int y1, int x2, int y2) {
        Assertions.assertEquals(new Vector2D(ox, oy), new Vector2D(x1, y1).multiply(new Vector2D(x2, y2)));
    }
    @Test
    void correctDistance() {
        class Reference {
            static double distance(@NotNull Vector2D p1, @NotNull Vector2D p2) {
                var x = Math.max(p1.x(), p2.x()) - Math.min(p1.x(), p2.x());
                var y = Math.max(p1.y(), p2.y()) - Math.min(p1.y(), p2.y());
                return Math.sqrt(x * x + y * y);
            }
        }
        var p1 = new Vector2D(-32, 25);
        var p2 = new Vector2D(-7, 420);
        Assertions.assertEquals(Reference.distance(p1, p2), p1.relativeTo(p2).length());
    }
    @Test
    void normalizeLengthNearOne() {
        Assertions.assertTrue(Math.abs(new Vector2D(10, 30).normalize().length() - 1) < 0.01);
    }
}
