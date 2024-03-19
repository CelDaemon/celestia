package net.voidgroup.celestia.example;

import net.voidgroup.celestia.glfw.GLFW;
import net.voidgroup.celestia.glfw.Window;
import net.voidgroup.celestia.util.Point;

public class Example {
    public static void main(String[] args) {
        try(var glfw = new GLFW()) {
            try(var window = new Window(new Point(500, 500),"boo")) {
                while (!window.shouldClose()) {
                    Thread.onSpinWait();
                    glfw.pollEvents();
                }
            }
        }
    }
}
