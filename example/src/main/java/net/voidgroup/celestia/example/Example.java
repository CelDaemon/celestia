package net.voidgroup.celestia.example;

import net.voidgroup.celestia.glfw.GLFW;
import net.voidgroup.celestia.util.Point;

public class Example {
    public static void main(String[] args) {
        try(var glfw = new GLFW()) {
            System.out.println(glfw.getVersionString());
            var window = glfw.createWindow(new Point(800, 800), "Test");
            window.close();
        }
    }
}
