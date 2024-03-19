package net.voidgroup.celestia.test;

import net.voidgroup.celestia.glfw.GLFW;
import net.voidgroup.celestia.glfw.Window;
import net.voidgroup.celestia.glfw.error.ErrorCode;
import net.voidgroup.celestia.glfw.error.GLFWException;
import net.voidgroup.celestia.util.Point;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        var window = new Window(new Point(100, 100), "Example", List.of(Window.WindowHint.VISIBLE.of(false)));
        window.close();
    }
    @Test
    void crashEmpty() {
        var ex = Assertions.assertThrows(GLFWException.class, () -> {
            var window = new Window(new Point(0, 0), "Example", null);
            window.close();
        });
        Assertions.assertEquals(ex.getCode(), ErrorCode.InvalidValue);
    }
}
