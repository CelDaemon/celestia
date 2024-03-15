package net.voidgroup.celestia.example;

import net.voidgroup.celestia.glfw.GLFW;
import net.voidgroup.celestia.util.Vec2;

public class Example {
    public static void main(String[] args) {
        try(var glfw = new GLFW()) {
            System.out.println(glfw.getVersionString());
            try(var window = glfw.createWindow(new Vec2<>(800, 800), "Test")) {

            }

        }
    }
}
