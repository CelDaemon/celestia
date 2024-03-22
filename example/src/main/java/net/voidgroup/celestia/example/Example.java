package net.voidgroup.celestia.example;

import net.voidgroup.celestia.glfw.GLFW;
import net.voidgroup.celestia.glfw.window.Monitor;
import net.voidgroup.celestia.glfw.data.Point2D;
import net.voidgroup.celestia.glfw.window.WindowBuilder;

public class Example {
    public static void main(String[] args) throws InterruptedException {
        try(var glfw = new GLFW()) {
            var window = new WindowBuilder("Test").fullscreen(Monitor.getPrimary()).build();
            while (true) {
                Thread.sleep(16);
            }
        }
    }
}
