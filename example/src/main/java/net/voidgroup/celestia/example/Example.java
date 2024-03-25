package net.voidgroup.celestia.example;

import net.voidgroup.celestia.glfw.GLFW;
import net.voidgroup.celestia.glfw.window.Monitor;
import net.voidgroup.celestia.glfw.window.WindowBuilder;

public class Example {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(GLFW.getVersion());
        try(var glfw = new GLFW()) {
            var window = new WindowBuilder("Test").fullscreen(Monitor.getPrimary()).build();
            while (!window.shouldClose()) {
                glfw.pollEvents();
                Thread.sleep(16);
            }
        }
    }
}
