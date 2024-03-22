package net.voidgroup.celestia.test;

import net.voidgroup.celestia.glfw.GLFW;
import net.voidgroup.celestia.glfw.window.WindowBuilder;
import net.voidgroup.celestia.glfw.window.WindowHint;
import net.voidgroup.celestia.glfw.error.ErrorCode;
import net.voidgroup.celestia.glfw.error.GLFWException;
import net.voidgroup.celestia.glfw.data.Point2D;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class GLFWTest {
    private static GLFW glfw;
    @BeforeAll
    static void setUp() {
        glfw = new GLFW();
    }
    @AfterAll
    static void closeAll() {
        glfw.close();
    }
    @Test
    void versionContains() {
        Assertions.assertTrue(glfw.getVersionString().contains("3.4"));
    }
    @Test
    void createWindow() {
        var window = new WindowBuilder("Example").size(new Point2D(500, 500)).hint(WindowHint.VISIBLE.of(false)).build();
        window.close();
    }
    @Test
    void crashEmpty() {
        var ex = Assertions.assertThrows(GLFWException.class, () -> {
            var window = new WindowBuilder("Example").build();
            window.close();
        });
        Assertions.assertEquals(ex.getCode(), ErrorCode.InvalidValue);
    }
}
