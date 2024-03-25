package net.voidgroup.celestia.glfw.window;

import net.voidgroup.celestia.glfw.data.Point2D;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.LinkedList;

@Nullable
public class WindowBuilder {
    @NotNull
    private final String title;
    private final Collection<WindowHint<?>> hints = new LinkedList<>();
    private Point2D size;
    private Monitor monitor;
    private Window shared;
    public WindowBuilder(@NotNull String title) {
        this.title = title;
    }
    public WindowBuilder size(Point2D size) {
        this.size = size;
        return this;
    }
    public WindowBuilder fullscreen(Monitor monitor) {
        this.monitor = monitor;
        return this;
    }
    public WindowBuilder shared(Window window) {
        this.shared = window;
        return this;
    }
    public WindowBuilder hint(WindowHint<?> hint) {
        hints.add(hint);
        return this;
    }
    public Window build() {
        return new Window(title, size, monitor, shared, hints);
    }

}
