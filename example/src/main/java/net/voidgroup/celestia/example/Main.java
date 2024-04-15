package net.voidgroup.celestia.example;

import net.voidgroup.celestia.glfw.GLFW;
import net.voidgroup.celestia.glfw.window.Window;

public class Main {
    public static void main() {
        try(var glfw = GLFW.initialise()) {
            var window = new Window.WindowBuilder(500, 500, "Test").build();
            while (true) {
                Thread.onSpinWait();
            }
        }
    }
}
