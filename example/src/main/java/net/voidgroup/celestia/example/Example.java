package net.voidgroup.celestia.example;

import net.voidgroup.celestia.glfw.GLFW;

public class Example {
    public static void main(String[] args) {
        try(var glfw = new GLFW()) {
            glfw.createWindow(0, 0, "Test");
            while(true) {
                Thread.onSpinWait();
            }
        }
    }
}
