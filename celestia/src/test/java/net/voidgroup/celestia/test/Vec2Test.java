package net.voidgroup.celestia.test;

import net.voidgroup.celestia.util.Vec2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Vec2Test {
    @ParameterizedTest
    @CsvSource(
            {
                    "20,20,10,10,2,2",
                    "15,30,5,5,3,6"
            })
    void testMultiply(int ox, int oy, int x1, int y1, int x2, int y2) {
        Assertions.assertEquals(new Vec2(ox, oy), new Vec2(x1, y1).multiply(new Vec2(x2, y2)));
    }
}
