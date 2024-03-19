package net.voidgroup.celestia.test;

import net.voidgroup.celestia.glfw.GLFW;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

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
//    @Test
//    void versionContains() {
//        Assertions.assertTrue(glfw.getVersionString().contains("3.4"));
//    }
//    @Test
//    void createWindow() {
//        glfw.createWindow(new Point(100, 100), "Example", null, Map.of(0x00020004, 0));
//    }
//    @Test
//    void crashEmpty() {
//        var ex = Assertions.assertThrows(GLFWException.class, () -> glfw.createWindow(new Point(0, 0), "Example", null, null));
//        Assertions.assertEquals(ex.getCode(), ErrorCode.InvalidValue);
//    }
}
