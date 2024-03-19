package net.voidgroup.celestia.example;

import net.voidgroup.celestia.glfw.GLFW;
import net.voidgroup.celestia.glfw.Window;
import net.voidgroup.celestia.util.Point;

public class Example {
    public static void main(String[] args) {
        try(var _ = new GLFW()) {
            try(var _ = new Window(new Point(10, 10),"boo")) {

            }
        }
    }
}
