package net.voidgroup.celestia.example;

import net.voidgroup.celestia.glfw.Color;
import net.voidgroup.celestia.glfw.GLFW;
import net.voidgroup.celestia.glfw.Window;
import net.voidgroup.celestia.util.Point;

import java.util.List;

public class Example {
    public static void main(String[] args) throws InterruptedException {
        try(var glfw = new GLFW()) {
            try(var window = new Window(new Point(500, 500),"Boo", List.of(
                    Window.WindowHint.VISIBLE.of(true),
                    Window.WindowHint.RESIZABLE.of(true),
                    Window.WindowHint.DECORATED.of(false),
                    Window.WindowHint.FLOATING.of(true),
                    Window.WindowHint.MAXIMIZED.of(true),
                    Window.WindowHint.CENTER_CURSOR.of(true),
                    Window.WindowHint.TRANSPARENT_FRAMEBUFFER.of(true)))) {
                var context = window.getContext();
                while (!window.shouldClose()) {
                    context.clear(Color.TRANS_PINK);
                    window.swap();
                    glfw.pollEvents();
                    Thread.sleep(16);
                }
            }
        }
    }
}
