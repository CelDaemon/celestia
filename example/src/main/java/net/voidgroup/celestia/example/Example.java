package net.voidgroup.celestia.example;

import net.voidgroup.celestia.glfw.GLFW;
import net.voidgroup.celestia.util.Point;

public class Example {
    public static void main(String[] args) {
        try(var glfw = new GLFW()) {
            System.out.println(glfw.getVersionString());
            try(var window = glfw.createWindow(new Point(800, 800), "Test")) {
                var context = window.getContext();

                while (!window.shouldClose()) {
                    context.clearColor((byte) 245, (byte) 169, (byte) 184, (byte) 255);
                    window.swap();
                    glfw.pollEvents();
                }
            }
        }
    }
}
