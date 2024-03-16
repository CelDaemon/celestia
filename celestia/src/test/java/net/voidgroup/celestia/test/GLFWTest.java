package net.voidgroup.celestia.test;

import net.voidgroup.celestia.glfw.ErrorCode;
import net.voidgroup.celestia.glfw.GLFW;
import net.voidgroup.celestia.glfw.GLFWException;
import net.voidgroup.celestia.util.Vec2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

public class GLFWTest {
    public static final Logger LOGGER = Logger.getLogger(GLFWTest.class.getSimpleName());
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
        glfw.createWindow(new Vec2(100, 100), "Example");
    }
    @Test
    void crashEmpty() {
        var ex = Assertions.assertThrows(GLFWException.class, () -> glfw.createWindow(new Vec2(0, 0), "Example"));
        Assertions.assertEquals(ex.getCode(), ErrorCode.InvalidValue);
        LOGGER.info(ex.getMessage());
    }
}
