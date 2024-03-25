package net.voidgroup.celestia.example;

import net.voidgroup.celestia.glfw.GLFW;
import net.voidgroup.celestia.glfw.data.Point2D;
import net.voidgroup.celestia.glfw.window.WindowBuilder;

public class Example {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(GLFW.getVersion());
        try(var glfw = new GLFW()) {
            try(var window = new WindowBuilder("Test")
                    .size(new Point2D(500, 500))
                    .resizable(false).build()) {
                var i = 0;
                while (!window.shouldClose()) {
                    glfw.pollEvents();
                    Thread.sleep(16);
                    if(i == 60 * 10) window.setResizable(true);
                    i++;
                }
            }

        }
    }
}
